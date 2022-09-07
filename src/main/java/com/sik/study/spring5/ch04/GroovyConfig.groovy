package com.sik.study.spring5.ch04

import org.springframework.beans.factory.groovy.GroovyBeanDefinitionReader
import org.springframework.context.support.GenericApplicationContext

def context = new GenericApplicationContext()
def reader = new GroovyBeanDefinitionReader(context)

reader.beans {
    singer(com.sik.study.spring5.ch03.xml.Singer, name: 'John Mayer', age: 39)
}

context.refresh()

println context.getBean("singer")