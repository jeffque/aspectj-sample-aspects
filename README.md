# aspectj-sample-aspects
Java AspectJ samples

**DummyAspect** is a sample aspect to show how to access to the 'join point' attributes and how to declare the 'advices'

**TimeWatch** is an annotation that takes time execution for the annotated method


## Computaria blog post related stuff

Execute **DummyAspect** as main class. Eg:

```bash
> mvn clean compile exec:java -Dexec.mainClass=mcprol.aspectj.dummy.DummyCounter
```

It will show the argument passed annotated with `DummyAnnotation`.