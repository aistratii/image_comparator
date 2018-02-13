package imagesearch;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.jndi.support.SimpleJndiBeanFactory;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Arrays.stream;

public class Main {

    public static void main(String args[]){
        //ApplicationContext appctx = new AnnotationConfigApplicationContext(ImageSearchConfig.class);
        //final MainService mainService = (MainService) appctx.getBean("mainService");
        //mainService.work(args);

        stream(args).forEach(System.out::println);
    }

}