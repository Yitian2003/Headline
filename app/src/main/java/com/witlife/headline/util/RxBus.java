package com.witlife.headline.util;

import java.util.List;

import javax.security.auth.Subject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

public class RxBus {

    private  RxBus(){

    }

    @NonNull
    public static RxBus getInstance(){return Holder.instance;}

    @NonNull
    public <T> Observable<T> register(@NonNull Class<T> clz){
        return register(clz.getName());
    }

    @NonNull
    public <T> Observable<T> register(@NonNull Object tag){
        List<Subject>
    }

    private static class Holder {
        private static RxBus instance = new RxBus();
    }
}
