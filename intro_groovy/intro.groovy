class Person {
def id
def name
}

Person john = new Person(name:"John Franandace")
println john.name
//Defining Closure
println "////////////////////////////////////Defining Closures/////////////////////////////////////////////////"
def myClosure = { println "Hello, "+it }

myClosure("puneet")
myClosure(name: "puneet", roll: 1137417841)

//Renaming Closure arguments ( specially it)
println("/////////////////////Renaming it/////////////////////////////")
def newClosure = {myVar->
println myVar
}

// Now name & pwd will be stored as a map in myVar
newClosure(name: "Puneet",pwd: 123)


//Strictly defining type of closure
def hCl = {String myVar->
println myVar
}

"puneet"

//Passing double variables

println "/////////////////////////////////////////Passing Double Variables//////////////////////////////////////////////"

def dclos = {String name, int roll->
println "Hello, ${name}"
println "your roll number is ${roll}"
}

dclos("puneet",13424)


//1.upto(5, dclos("dsd",43242))

1.upto(5, myClosure)


//for user-defined class type don;t need to specify ${p} just write $p but $p.name is wrong (Correct is ${p.name}) 



//Another way of for loop

3.times{println it}
3.times {print it}


0.step(10,2) {print it+" "}

// from 0 to 10 step/increment by 2

//112123213.step(424141344141, 32442342) {println it}
32213414124124.class


///



//Navigation operator or safe referencial operator
String str=null
str?.reverse()

//str?.reverse().reverse()
//str.reverse()

"ls".execute().getText()


def ref = new File("abc.txt")
ref.class.name


//?: //Elvis operator

def x=10;
def y=x?x:20

def a=null
def b=a?:11




def xyz= [1,2,3.5,342142354453425,65,[],"dasdada"]
xyz<<4234
xyz

def myMap = [name:"Puneet", "your name":30]
myMap.each{println it}
myMap.getClass().name

xyz<<myMap

xyz


Map<String, Integer> newMap = ["name": "puneet", "marks": 423423]

class Person1 {

static sayHello(){println this}
class BB {
   // Person1.sayHello()
}
}
Person1.sayHello()


//xyz.class.name
//myMap.class.name


class Session {
     String getName() {"groovy"}
}

new Session().name




//void show(var1, var2=10) { println var1+var2}
void show(var1, var2=10) { println var1+var2}

show(22)
show("d",232)
show 23
show 12,5
show "dasdsa", 232



class MyClass {
int x
MyClass plus(MyClass other) {
    new MyClass(x:this.x+other.x)
}
}

MyClass m = new MyClass(x:2)


1.upto(5){println it}




class Semi {
    def val = 3
    {
        println "HEllo"
        }
}


class Semi1 {
    def val = 3;
    {
        println "HEllo"
        }
}

println "<>" * 30


//joint compile