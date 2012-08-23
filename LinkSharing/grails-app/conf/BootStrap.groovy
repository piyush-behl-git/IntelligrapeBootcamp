class BootStrap {

    def init = { servletContext ->

        initUsers()

        initTopic("Java", Visibility.PRIVATE,'javajooba@gmail.com')
        initTopic('Groovy & Grails', Visibility.PRIVATE, 'puneet.behl007@gmail.com')
        initTopic( 'html', Visibility.PUBLIC, 'puneet.behl007@gmail.com')
        initTopic('JQuery', Visibility.PUBLIC, 'admin@intelligrape.com')

        5.times {
            initTopic("Topic${it}", Visibility.PUBLIC,it+"@intelligrape.com")
        }

        subscribe()

        randomSubscribe()

        initResources()
        initReadingItems()

//        randomRead([1, 5 , 8])

    }
    def destroy = {
    }

    void initUsers() {

        println "Initializing Users"

        def vijay= new User(email: 'javajooba@gmail.com',password: 'admin123', confirmPassword: 'admin123', fullName: "Vijay Kumar", isMale: true)
        vijay.save(failOnError: true)

        def puneet = new User(email: 'puneet.behl007@gmail.com', password: '123456789', confirmPassword: '123456789', fullName: 'Puneet Behl', isMale: true)
        puneet.save(failOnError: true)

        def admin = new User(email: 'admin@intelligrape.com', password: 'igdefault', confirmPassword: 'igdefault', fullName: 'Administrator', isMale: true)
        admin.save(failOnError: true)

        5.times {
            def user = new User(email: it+"@intelligrape.com", password: 'igdefault', confirmPassword: 'igdefault', fullName: "Name${it}", isMale: false)
            println user.email
            user.save(failOnError: true)
        }
        println "Users initialized"
    }
    void initTopic(String topicName,Visibility visibility,String email) {
        def owner = User.findByEmail(email)

        if(owner)   {
            Topic topic = new Topic(name: topicName, visibility: visibility, owner: owner)
            topic.save(failOnError: true)
            subscribeTopic(owner,topic, Seriousness.VERY_SERIOUS)
        }
        else
            println "User Not found"
    }

    void randomSubscribe() {

        println "Random Subscribe"

        List<Topic> topics = Topic.list()
        for(topic in topics)    {
            List<User> users = User.list()
            println topic.owner
            users=users-topic.owner
            println "List withoout owner "
            println users
            Collections.shuffle(users)

            subscribeTopic(users.first(), topic, Seriousness.CASUAL)
            subscribeTopic(users.last(), topic, Seriousness.SERIOUS)

            println "${users.first()} subscribed to ${topic}"
            println "${users.last()} subscribed to ${topic}"
        }
    }

    void subscribe() {
        User user = User.findByEmail('javajooba@gmail.com')
        Topic topic = Topic.findByName('Java')
        if(topic)
        subscribeTopic(user, topic, Seriousness.SERIOUS)

        user = User.findByEmail('admin@intelligrape.com')
        topic = Topic.findByName('html')
        if(topic)
            subscribeTopic(user, topic, Seriousness.SERIOUS)

        println "All subcriptions completed successfully..."
    }

    void initResources() {
        println "Seaching for all topics....."
        List topics = Topic.list()

        println "Topics found"
        println topics*.name

        LinkResource linkResource
        DocumentResource documentResource

        for (topic in topics) {

            println "Adding document resource ..."
            documentResource = new DocumentResource(fileName: "Doc"+topic.name)
            documentResource.save(failOnError: true)
            topic.addToResources(documentResource)
            topic.save(failOnError: true)


            println "Adding link resouce to ${topic.name}..."

            10.times {
                linkResource = new LinkResource(title: "${topic.name} ${it}", url: "http://www.enfopedia.com/${it}", summary: "Details of topic ${topic.name} ${topic.id}")
                topic.addToResources(linkResource)

                linkResource.save(failOnError: true)
                println "Resource ${linkResource.title} ${linkResource.url} added successfully..."
            }
            topic.save(failOnError: true)

            println "Resources successfully added to ${topic.name} ;)"
        }
    }

    void initReadingItems() {

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
            println subscriptions

            for (subscription in subscriptions) {

                println "Searching for subscriber..."

                subscriber = subscription.subscriber

                println "${subscriber.fullName} found."
                println "Initializing reading items..."

                for (resource in resources) {

                    ReadingItem readingItem = new ReadingItem(user: subscriber, resource: resource, isFavorite: false, isRead: false)
                    readingItem.save(failOnError: true)

                    println "Item ${readingItem.id} intialized with ${resource.title} by ${subscriber.fullName}"
                }
            }
        }
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
        println "Randomly marking reading items as isRead"

        for (index in list) {
            ReadingItem readingItem = readingItems[index]
            readingItem.isRead = true
            readingItem.save(failOnError: true)
        }

        println "Mark isRead completed"
    }

    void subscribeTopic(User user,Topic topic,Seriousness seriousness) {
        Subscription subscription
        if (topic.visibility == Visibility.PUBLIC || (topic.visibility == Visibility.PRIVATE && topic.owner == user))  {
            subscription = new Subscription(subscriber: user, topic: topic, seriousness: seriousness)
            user.addToTopics topic
            user.save(failOnError: true)
            subscription.save(failOnError: true)
            topic.addToSubscriptions(subscription)
            topic.save(failOnError: true)
        }

    }
}
import com.ig.bc.User
import com.ig.bc.Topic
import com.ig.bc.enums.Seriousness
import com.ig.bc.Resource
import com.ig.bc.Subscription
import com.ig.bc.ReadingItem
import com.ig.bc.LinkResource

import com.ig.bc.enums.Visibility
import com.ig.bc.DocumentResource
