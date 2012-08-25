List l = []
l[11] = "myelement"
l.get(5)
l

l = (0..9) as List
l+=4
println l
Set s = l as Set
println s

println l.unique()

println ([11,12,13,14]-[13,14,15])

l.disjoint([21,34])


l= [1,2,3,"element1",0.3,[2,4,6],0..10]
println l*.class
l.get(6).get(9)

[14,12,11,10,16,15,,12,10,99,90,14,16,35].unique().sort().reverse()

String s1 = "this string needs to be split"
println s1.tokenize(" ")
println s1.tokenize()

s1 = "this string needs to be split"
println s1.split(" ")
println s1.split(/\s/)

println s1.tokenize(/\s/)

s1 = "are.you.trying.to.split.me.mister?"
println s1.tokenize(".")
println s1.split(".")
Range r = (5..9)
r.from
r.from.next()
r.from.previous()
(1..10).each{ println "2 x $it : ${2*it}" }

('a'..'z')-('a'..'j')

Map frens = ["Vineet":23, "Deepika": 30, "Puneet":22, "Himanshu": 20]

[1,2,3,4,5,6,7,8,9,10].groupBy{if(it%2)return "odd" else return "even"}


class Employee {
    String name
    Integer age
    String department
    Integer salary    
}

Employee e1 = new Employee(name:"Amit", department:"Computer", age:222, salary: 5000)
Employee e2 = new Employee(name:"Aman", department:"Computer", age:212, salary: 15000)
Employee e3 = new Employee(name:"Amsd", department:"Civil", age:21, salary: 35000)
Employee e4 = new Employee(name:"Amisd", department:"Civil", age:252, salary: 25000)
Employee e5 = new Employee(name:"aman s", department:"English", age:2, salary: 500)
Employee e6 = new Employee(name:"Bman s", department:"English", age:2, salary: 6000)
l = [e1,e2,e3,e4,e5,e6]
println l.groupBy {
            if(it.salary in (0..5000)) {
                return "0-5000"
                }
            else if(it.salary in (5001..10000)) 
                return "5000-10000"
                
             return ">10000"
}
println (l.groupBy {it.department})

println l.groupBy{if(it.age in (18..35)) return "18-35"
return ">35"}
def ll=l.groupBy {
    it.department
}
ll.each {
    println it.key +" "+it.value.size()
}

ll = l.groupBy {
    it.name.charAt(0).toUpperCase()
}

ll.each {gp->
        println gp.key 
        println gp.value.findAll {
            it.age>20
        }
}
            
            
 println "Employee Salary less than 5000"+ l.findAll{it.salary<5000}
 int age;
 l.max {
     it.age;
 }.name
 l.min {it.age}.name
 
 l.findAll{it=l.max{emp->
 emp.salary}.salary}
 l*.name
 
 String str  = "find the number of occurances of character in a String"
 str.split("").findAll{it=='n'}.size()
 (1..100).each{
     if(!(it%15))
         print "FizzBuzz "
     else if(!(it%3))
         print "Fizz "
     else if(!(it%5))
         print "Buzz "
     else 
         print it+" "
 }
 
 frens*.key
 frens*.value
 println ""
 frens.each{
 print it
 print " "}
 println ""
 
 frens+['a':12, 'e':323, 'r':"dada"]
 
 frens.class
 frens.getClass()
 
 Map m = ['1' : 2, '2' : 3, '3' : 4, '2':5]
m['2']
m.containsKey('1')

m = ['Computing' : ['Computing' : 600, 'Information Systems' : 300],'Engineering' : ['Civil' : 200, 'Mechanical' : 100],'Management' : ['Management' : 800] ]
m.keySet().count{it}
println m.get('Computing').count{it}
m.keySet().count{it}
println m.get('Engineering').get('Civil')


class Stack {
    List l=[]
    String pop() {
        l.pop()
    }
    Boolean push(String item) {
        l.add(item)
    }
    String top () {
        l.last()
    }
    String toString() {
        l
    }
}

Stack myStack = new Stack()
myStack.push("Puneet")
myStack.push("Shree Ram")
println myStack

println myStack.top()
println myStack
println myStack.pop()
myStack