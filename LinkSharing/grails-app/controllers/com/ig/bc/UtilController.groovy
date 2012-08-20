package com.ig.bc

import com.ig.bc.enums.Seriousness
import com.ig.bc.enums.Visibility

class UtilController {

    def index() { }

    def create() {
          initUserTopic()
     }

    def subscribe() {
        User vijay = User.findByEmail('javajooba@gmail.com')

        println "User ${vijay.fullName} found :-)"

        Topic javaTopic = Topic.findByName('Java')
        Topic htmlTopic = Topic.findByName('html')

        println "Topic ${javaTopic.name} & ${htmlTopic.name} found...."
        println "Now ${vijay.fullName} is subscribing to ${htmlTopic.name} ${javaTopic.name}"

        subscribeTopic(vijay, javaTopic,Seriousness.VERY_SERIOUS)
        subscribeTopic(vijay, htmlTopic, Seriousness.SERIOUS)

        User puneet = User.findByEmail('puneet.behl007@gmail.com')

        println "User ${puneet.fullName} found :)"

        Topic groovyGrailsTopic = Topic.findByName('Groovy & Grails')

        println "Topic ${groovyGrailsTopic.name} found ....."
        println "${puneet.fullName} is subscribing to ${htmlTopic.name} ${groovyGrailsTopic.name}"

        subscribeTopic(puneet, groovyGrailsTopic, Seriousness.VERY_SERIOUS)
        subscribeTopic(puneet, htmlTopic, Seriousness.SERIOUS)

        User admin = User.findByEmail('admin@intelligrape.com')

        println "User ${admin.fullName} found :)"

        Topic jqueryTopic = Topic.findByName('JQuery')

        println "Topic ${jqueryTopic.name} found ....."
        println "${admin.fullName} is subscribing to ${htmlTopic.name} ${jqueryTopic.name}"

        subscribeTopic(admin, jqueryTopic, Seriousness.VERY_SERIOUS)
        subscribeTopic(admin, htmlTopic, Seriousness.SERIOUS)

        println "All subcriptions completed successfully..."
    }

    def initResources() {
        createResource()
    }

    def initReadingItems() {

        println "Initializing reading items... "
        println "Searching for topics..."

        List topics = Topic.list()

        println "Topics found."
        println topics*.name

        for (topic in topics) {

            println "Searching for resources in ${topic}..."

            Set<Resource> resources = topic.resources

            println "Search completed."
            println resources*.title

            User subscriber

            println "Seaching for subscriptions..."

            Set<Subscription> subscriptions = topic.subscriptions

            println "Subscriptions found."

            for (subscription in subscriptions) {

                println "Searching for subscriber..."

                subscriber = subscription.subscriber

                println "${subscriber.fullName} found."
                println "Initializing reading items..."

                for (resource in resources) {

                    ReadingItem readingItem = new ReadingItem(user: subscriber, resource: resource)
                    readingItem.save(failOnError: true)

                    println "Item ${readingItem.id} intialized with ${resource.title} by ${subscriber.fullName}"
                }
            }
        }
    }

    def markRead() {
        randomRead([1, 5 , 8])
    }

    def update() {

    }

    def delete() {

    }

    def read() {

    }

    def unreadResources() {
        unread("puneet.behl007@gmail.com")
    }

    void randomRead(list) {

        println "fetxhing all reading items..."

        List<ReadingItem> readingItems = ReadingItem.list()

        println "fetch completed."
        println readingItems
        println "shuffling list..."

        Collections.shuffle(readingItems)

        println "Shuffle completed."
        println readingItems
        println "Randomly marking reading items as read"

        for (index in list) {
            ReadingItem readingItem = readingItems[index]
            readingItem.read = true
            readingItem.save(failOnError: true)
        }

        println "Mark read completed"
    }

    void createResource() {

        println "Seaching for all topics....."
        List topics = Topic.list()

        println "Topics found"
        println topics*.name
        int count=1
        LinkResource linkResource
        for (topic in topics) {

            println "Adding link resouce to ${topic.name}..."

            10.times {
                linkResource = new LinkResource(title: "${topic.name} ${count}", url: "http://www.enfopedia.com/${count}", summary: "Details of topic ${topic.name} ${topic.id}")
                topic.addToResources(linkResource)

                linkResource.save(failOnError: true)

                println "Resource ${linkResource.title} ${linkResource.url} added successfully..."

                count++
            }
            topic.save(failOnError: true)

            println "Resources successfully added to ${topic.name} ;)"
        }
    }

    void subscribeTopic(User user, Topic topic, Seriousness seriousness) {
        Subscription subscription
        if (topic.visibility == Visibility.PUBLIC || (topic.visibility == Visibility.PRIVATE && topic.owner == user))  {
            subscription = new Subscription(subscriber: user, topic: topic, seriousness: seriousness)
            subscription.save(failOnError: true)
        }

    }

    void unread(email) {

        println "Looking for user.."

        User user = User.findByEmail(email)

        println "User found:  ${user.fullName}"
        println "Looking for ${user.fullName} resources"

        List<ReadingItem> readingItems = ReadingItem.findAllByUserAndRead(user, false)
        List resources = readingItems*.resource
        println resources*.url
    }

    void createDoc() {

    }

    void initUserTopic() {

        User vijay= new User(email: 'javajooba@gmail.com',password: 'admin123', fullName: "Vijay Kumar", male: true)
        vijay.save(failOnError: true)

        User puneet = new User(email: 'puneet.behl007@gmail.com', password: '123456789', fullName: 'Puneet Behl', male: true)
        puneet.save(failOnError: true)

        User admin = new User(email: 'admin@intelligrape.com', password: 'igdefault', fullName: 'Administrator', male: true)
        admin.save(failOnError: true)


        Topic javaTopic = new Topic(name: "Java", visibility: Visibility.PRIVATE, owner: vijay)
        javaTopic.save()

        Topic groovyGrailsTopic = new Topic(name: 'Groovy & Grails', visibility: Visibility.PRIVATE, owner: puneet)
        groovyGrailsTopic.save()

        Topic htmlTopic = new Topic(name: 'html', visibility: Visibility.PUBLIC, owner: puneet)
        htmlTopic.save()

        Topic jqueryTopic = new Topic(name: 'JQuery', visibility: Visibility.PUBLIC, owner: admin)
        jqueryTopic.save(failOnError: true)

        vijay.addToTopics javaTopic
        vijay.addToTopics htmlTopic
        vijay.save(failOnError: true)

        puneet.addToTopics groovyGrailsTopic
        puneet.addToTopics htmlTopic
        puneet.save(failOnError: true)

        admin.addToTopics jqueryTopic
        admin.addToTopics htmlTopic
        admin.save(failOnError: true)
    }
}