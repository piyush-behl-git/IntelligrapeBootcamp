println "*"
0.step(9,2) {println "*"*it}

List myList = [1,4,6,64,"ddasdf",56,"efsadf"]
int count=0
int sum=0
myList.each {
    String tmp=it
    if(tmp.isNumber())    {
    sum+=it
    count++
    }
}
sum/count

class HourMinutes {
    int hrs
    int min
    
    HourMinutes plus(HourMinutes time)    {
    HourMinutes tmp=new HourMinutes();
    tmp.hrs=this.hrs+time.hrs
    tmp.min=this.min+time.min
    if(tmp.min>60)    {
        tmp.hrs+=tmp.min/60
        tmp.min=tmp.min%60
    }
    return tmp;
    }
    
    HourMinutes minus(HourMinutes time)    {
    HourMinutes tmp=new HourMinutes();
    tmp.hrs=this.hrs-time.hrs
    tmp.min=this.min-time.min
    if(tmp.min<0)    {
        tmp.hrs-=1
        tmp.min+=60
    }
    return tmp;
    }
    String toString() {
        return "Time : "+hrs+" hrs "+min+" mins"
    }
}
HourMinutes time1 = new HourMinutes(hrs: 4, min: 30)
HourMinutes time2 = new HourMinutes(hrs: 2, min: 40)
time1+time2
time1-time2

def find = {def name,List nameList->
    int cnt=0
    nameList.each {
        if(name==it)    {
           
            println "${it} found @ position ${cnt}"
        } 
        cnt++
    }
}


1.upto(10) {println "3 x $it : ${3*it}"}
1.step(11,1) {println "3 x $it : ${3*it}"}
11.times {if(it)
    println "3 x $it : ${3*it}"}

find("puneet", ["komal", "nanu", "rajan", "sumit", "puneet", "vineet"])

if("Test") { 
    println "test evaluated to true inside if" 
}
if('null') { println "test evaluated to true inside if" }

if(100) { println "test evaluated to true inside if" }

if (0) { println "test evaluated to true inside if" }

if([]) { println "test evaluated to true inside if" }

if([null, null]) { println "test evaluated to true inside if" }
"touch /home/intelligrape/bootcamp/session3/newFile.txt".execute().text
File newFile= new File("/home/intelligrape/bootcamp/session3/newFile.txt")
File dir = new File("/home/intelligrape/bootcamp/session2/htmlSession/")
dir.eachFile {
    newFile.append(new File("$it").text)
}
"touch /home/intelligrape/bootcamp/session3/newTestFile.txt".execute().text
File myFile = new File("/home/intelligrape/bootcamp/session2/htmlSession/one.html")
int cwnt=1
myFile.eachLine {
    if(cwnt%2)    {
        new File("/home/intelligrape/bootcamp/session3/newTestFile.txt").append(cwnt+" "+it+ "\n") 
        
   }
   cwnt++;
}

def content = new File("/home/intelligrape/bootcamp/session3/intro_groovy/xyz.txt").text
def data=content.tokenize()
data.each {print it}
println ""


def inputFile = new File("/home/intelligrape/bootcamp/session3/intro_groovy/abc.txt")
int lno=1;
    int wno=1;
    def findW = {File iFile, String word->
iFile.eachLine {
   def line = it.split(" ")
   //println line
   line.each {String w->
       if(w==word)    {
           println "$word is found @ line $lno @ word #$wno" 
       }
       wno++
   }
    lno++
}
}
findW(inputFile, "apple")