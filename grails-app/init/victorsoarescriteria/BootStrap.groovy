package victorsoarescriteria

import grails.gorm.transactions.Transactional

class BootStrap {

    def init = { servletContext ->
        initData()
    }
    def destroy = {
    }

    @Transactional
    void initData() {
        new SampleClass(name: 'Jeff', country: 'United States', someNumber: 42).save()
        new SampleClass(name: 'Jake', country: 'United States', someNumber: 21).save()
        new SampleClass(name: 'Bill', country: 'United Kingdom', someNumber: 27).save()

        def greaterThanFive = search {
            gt 'someNumber', 5
        }
        println "greaterThanFive.size() is ${greaterThanFive.size()}"
        def greaterThanTwentyFive = search {
            gt 'someNumber', 25
        }
        println "greaterThanTwentyFive.size() is ${greaterThanTwentyFive.size()}"
    }

    def search(predicate) {
       SampleClass.withCriteria {
           predicate.delegate =  delegate
           predicate()
       }
    }
}
