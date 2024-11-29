package org.react.tutorials.exercise.assignment1;

import org.react.tutorials.common.Util;

public class Assignment1 {

    public static void main(String[] args) {
        FileServiceImp service = new FileServiceImp();
        service.write("ahoy.txt", "hello there!").subscribe(Util.subscriber());
        Util.sleepSeconds(2);
        service.read("ahoy.txt").subscribe(Util.subscriber());
        Util.sleepSeconds(2);
        service.delete("ahoy.txt").subscribe(Util.subscriber());
    }
}
