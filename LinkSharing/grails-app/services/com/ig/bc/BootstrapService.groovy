package com.ig.bc

import com.ig.bc.enums.Seriousness
import com.ig.bc.enums.Visibility

class BootstrapService {

    def topicSubscriptionService
    def readingItemService
    def resourceService
    def userService


    def initializeData() {
        initializeUsers()
        initializeTopics()
        initializeResources()
        initializeReadingItems()
        markRandomlyAsRead([1, 5, 8])
    }

    void initializeUsers() {
        def vijay = new User(email: 'javajooba@gmail.com', password: 'admin123', confirmPassword: 'admin123', fullName: "Vijay Kumar", isMale: true)
        vijay.save(failOnError: true)
        def puneet = new User(email: 'puneet.behl007@gmail.com', password: '123456789', confirmPassword: '123456789', fullName: 'Puneet Behl', isMale: true)
        puneet.save(failOnError: true)
        def admin = new User(email: 'admin@intelligrape.com', password: 'igdefault', confirmPassword: 'igdefault', fullName: 'Administrator', isMale: true)
        admin.save(failOnError: true)
        def hr = new User(email: 'hr@ig.com', password: 'hr', confirmPassword: 'hr', fullName: 'hr', isMale: true)
        hr.save(failOnError: true)

        5.times {
            def user = new User(email: it + "@intelligrape.com", password: 'igdefault', confirmPassword: 'igdefault', fullName: "Name${it}", isMale: false)
            user.save(failOnError: true)
        }
    }

    void initializeTopics() {
        createAndSubscribeTopic("Java", Visibility.PRIVATE, 'javajooba@gmail.com')
        createAndSubscribeTopic('Groovy & Grails', Visibility.PRIVATE, 'puneet.behl007@gmail.com')
        createAndSubscribeTopic('html', Visibility.PUBLIC, 'puneet.behl007@gmail.com')
        createAndSubscribeTopic('JQuery', Visibility.PUBLIC, 'admin@intelligrape.com')
        createAndSubscribeTopic('css', Visibility.PUBLIC, 'hr@ig.com')
        5.times {
            createAndSubscribeTopic("Topic${it}", Visibility.PUBLIC, it + "@intelligrape.com")
        }
        findUserAndSubscribeTopic()
        subscribeRandomTopics()
    }

    void createAndSubscribeTopic(String topicName, Visibility visibility, String email) {
        User owner = User.findByEmail(email)
        if (owner) {
            Topic topic = new Topic(name: topicName, visibility: visibility, owner: owner)
            topic.save(failOnError: true)
            subscribeTopic(owner, topic, Seriousness.VERY_SERIOUS)
        }
    }

    void findUserAndSubscribeTopic() {
        User user = User.findByEmail('javajooba@gmail.com')
        Topic topic = Topic.findByName('Java')
        if (topic)
            subscribeTopic(user, topic, Seriousness.SERIOUS)
        user = User.findByEmail('admin@intelligrape.com')
        topic = Topic.findByName('html')
        if (topic)
            subscribeTopic(user, topic, Seriousness.SERIOUS)
    }

    void subscribeTopic(User user, Topic topic, Seriousness seriousness) {
        if (topic.visibility == Visibility.PUBLIC || (topic.visibility == Visibility.PRIVATE && topic.owner == user)) {
            Subscription subscription = new Subscription(subscriber: user, topic: topic, seriousness: seriousness)
            subscription.save(failOnError: true)
        }
    }

    void subscribeRandomTopics() {
        List<Topic> topics = Topic.list()
        for (topic in topics) {
            List<User> users = User.list()
            users = users - topic.owner
            Collections.shuffle(users)
            subscribeTopic(users.first(), topic, Seriousness.CASUAL)
            subscribeTopic(users.last(), topic, Seriousness.SERIOUS)
        }
    }

    void initializeResources() {
        List topics = Topic.list()
        LinkResource linkResource
        DocumentResource documentResource
        for (topic in topics) {
            int count = 1
            documentResource = new DocumentResource(fileName: "Doc" + topic.name)
            topic.addToResources(documentResource)
            topic.save(failOnError: true)
            count++
            10.times {
                linkResource = new LinkResource(title: "${topic.name} ${it}", url: "http://www.enfopedia.com/${it}", summary: "Details of topic ${topic.name} ${topic.id}")
                topic.addToResources(linkResource)
            }
            topic.save(failOnError: true)
        }
    }

    void initializeReadingItems() {
        List topics = Topic.list()
        for (topic in topics) {
            Set<Resource> resources = topic.resources
            User subscriber
            Set<Subscription> subscriptions = topic.subscriptions
            for (subscription in subscriptions) {
                subscriber = subscription.subscriber
                for (resource in resources) {
                    ReadingItem readingItem = new ReadingItem(user: subscriber, resource: resource, isFavorite: false, isRead: false)
                    readingItem.save(failOnError: true)
                }
            }
        }
    }

    void markRandomlyAsRead(list) {
        List<ReadingItem> readingItems = ReadingItem.list()
        Collections.shuffle(readingItems)
        for (index in list) {
            ReadingItem readingItem = readingItems[index]
            readingItem.isRead = true
            readingItem.save(failOnError: true)
        }
    }
}