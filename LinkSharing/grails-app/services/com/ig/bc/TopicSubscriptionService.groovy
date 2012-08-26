package com.ig.bc

import com.ig.bc.enums.Seriousness
import com.ig.bc.enums.Visibility

class TopicSubscriptionService {

    def serviceMethod() {

    }

    def initializeTopics() {
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
        def owner = User.findByEmail(email)

        if (owner) {
            Topic topic = new Topic(name: topicName, visibility: visibility, owner: owner)
            topic.save(failOnError: true)
            subscribeTopic(owner, topic, Seriousness.VERY_SERIOUS)
        }
        else
            println "User Not found"
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
        println "All subcriptions completed successfully..."
    }

    void subscribeRandomTopics() {
        println "Random Subscribe"
        List<Topic> topics = Topic.list()
        for (topic in topics) {
            List<User> users = User.list()
            println topic.owner
            users = users - topic.owner
            println "List withoout owner "
            println users
            Collections.shuffle(users)

            subscribeTopic(users.first(), topic, Seriousness.CASUAL)
            subscribeTopic(users.last(), topic, Seriousness.SERIOUS)

            println "${users.first()} subscribed to ${topic}"
            println "${users.last()} subscribed to ${topic}"
        }
    }

    void subscribeTopic(User user, Topic topic, Seriousness seriousness) {
        Subscription subscription
        if (topic.visibility == Visibility.PUBLIC || (topic.visibility == Visibility.PRIVATE && topic.owner == user)) {
            subscription = new Subscription(subscriber: user, topic: topic, seriousness: seriousness)
            user.addToTopics topic
            user.save(failOnError: true)
            subscription.save(failOnError: true)
            topic.addToSubscriptions(subscription)
            topic.save(failOnError: true)
        }
    }
}
