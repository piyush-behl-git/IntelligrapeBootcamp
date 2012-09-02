package com.ig.bc

import com.ig.bc.enums.Seriousness
import com.ig.bc.enums.Visibility

class BootstrapService {

    def initializeApplicationWithTestData() {
        initializeTestData()
        subscribeRandomTopics()
        markRandomlyAsRead([1, 5, 8])
    }

    void initializeTestData() {
        def vijay = new User(email: 'javajooba@gmail.com', password: 'admin123', confirmPassword: 'admin123', fullName: "Vijay Kumar", isMale: true)
        vijay.save(failOnError: true)
        createTopicAndInitializeResources("Java", Visibility.PUBLIC, vijay)
        def puneet = new User(email: 'puneet.behl007@gmail.com', password: '123456789', confirmPassword: '123456789', fullName: 'Puneet Behl', isMale: true)
        puneet.save(failOnError: true)
        createTopicAndInitializeResources("Groovy & Grails", Visibility.PUBLIC, puneet)
        createTopicAndInitializeResources("html", Visibility.PUBLIC, puneet)
        def admin = new User(email: 'admin@intelligrape.com', password: 'igdefault', confirmPassword: 'igdefault', fullName: 'Administrator', isMale: true)
        admin.save(failOnError: true)
        createTopicAndInitializeResources("Jquery", Visibility.PRIVATE, admin)
        def hr = new User(email: 'hr@ig.com', password: 'hr', confirmPassword: 'hr', fullName: 'hr', isMale: true)
        hr.save(failOnError: true)
        createTopicAndInitializeResources("HRM", Visibility.PRIVATE, hr)

        5.times {
            def user = new User(email: it + "@intelligrape.com", password: 'igdefault', confirmPassword: 'igdefault', fullName: "Name${it}", isMale: false)
            user.save(failOnError: true)
            createTopicAndInitializeResources("Topic${it}", Visibility.PUBLIC, user)
        }
    }

    void createTopicAndInitializeResources(String topicName, Visibility visibility, User owner) {
        Topic topic = new Topic(name: topicName, visibility: visibility, owner: owner)
        initializeResourcesInTopicAndSubscribe(topic)
    }

    void initializeResourcesInTopicAndSubscribe(Topic topic) {
        topic.addToResources(new DocumentResource(title: "${topic.owner}_${topic}_Doc", fileName: "Doc${topic.name}", owner: topic.owner))
        10.times {
            topic.addToResources(new LinkResource(title: "${topic.name} ${it}", url: "http://www.enfopedia.com/${it}",
                    summary: "Details of topic ${topic.name} ${topic.id}", owner: topic.owner))
        }
        topic.save(failOnError: true)
        subscribeTopicAndInitializeReadingItems(topic.owner, topic, Seriousness.VERY_SERIOUS)
    }

    void subscribeTopicAndInitializeReadingItems(User subscriber, Topic topic, Seriousness seriousness) {
        if (topic.visibility == Visibility.PUBLIC || (topic.visibility == Visibility.PRIVATE && topic.owner == subscriber)) {
            Subscription subscription = new Subscription(subscriber: subscriber, topic: topic, seriousness: seriousness)
            subscription.save(failOnError: true)
            initializeReadingItem(subscriber, topic)
        }
    }

    void initializeReadingItem(User subscriber, Topic topic) {
        Set<Resource> resources = topic.resources
        resources.each { resource ->
            ReadingItem readingItem = new ReadingItem(user: subscriber, resource: resource, isFavorite: false, isRead: false)
            readingItem.save(failOnError: true)
        }
    }

    void subscribeRandomTopics() {
        List<Topic> topics = Topic.list()
        for (topic in topics) {
            List<User> users = User.list() - topic.owner
            Collections.shuffle(users)
            subscribeTopicAndInitializeReadingItems(users.first(), topic, Seriousness.CASUAL)
            subscribeTopicAndInitializeReadingItems(users.last(), topic, Seriousness.SERIOUS)
        }
    }

    void markRandomlyAsRead(numbers) {
        List<ReadingItem> readingItems = ReadingItem.list()
        Collections.shuffle(readingItems)
        numbers.each {number ->
            ReadingItem readingItem = readingItems[number] as ReadingItem
            readingItem.isRead = true
            readingItem.save(failOnError: true)
        }
    }
}