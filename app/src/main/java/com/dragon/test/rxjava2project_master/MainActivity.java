package com.dragon.test.rxjava2project_master;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Button btnSendRx2;
    private Button btnSendRx22;
    private Button btnSendRx23;
    private Button btnSendRx24;
    private Button btnSendRx25;
    private Button btnSendRx26;
    private Button btnSendRx27;
    private Button btnSendRx28;
    private Button btnSendRx29;
    private Button btnSendRx210;
    private Button btnSendRx211;
    private Button btnSendRx212;
    private Button btnSendRx213;
    private Button btnSendRx214;
    private Button btnSendRx215;
    private Button btnSendRx216;
    private Button btnSendRx217;
    private Button btnSendRx218;
    private Button btnSendRx219;
    private Button btnSendRx220;
    private Button btnSendRx221;
    private Button btnSendRx222;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSendRx2 = findViewById(R.id.btn_send_rx2);
        btnSendRx22 = findViewById(R.id.btn_send_rx2_2);
        btnSendRx23 = findViewById(R.id.btn_send_rx2_3);
        btnSendRx24 = findViewById(R.id.btn_send_rx2_4);
        btnSendRx25 = findViewById(R.id.btn_send_rx2_5);
        btnSendRx26 = findViewById(R.id.btn_send_rx2_6);
        btnSendRx27 = findViewById(R.id.btn_send_rx2_7);
        btnSendRx28 = findViewById(R.id.btn_send_rx2_8);
        btnSendRx29 = findViewById(R.id.btn_send_rx2_9);
        btnSendRx210 = findViewById(R.id.btn_send_rx2_10);
        btnSendRx211 = findViewById(R.id.btn_send_rx2_11);
        btnSendRx212 = findViewById(R.id.btn_send_rx2_12);
        btnSendRx213 = findViewById(R.id.btn_send_rx2_13);
        btnSendRx214 = findViewById(R.id.btn_send_rx2_14);
        btnSendRx215 = findViewById(R.id.btn_send_rx2_15);
        btnSendRx216 = findViewById(R.id.btn_send_rx2_16);
        btnSendRx217 = findViewById(R.id.btn_send_rx2_17);
        btnSendRx218 = findViewById(R.id.btn_send_rx2_18);
        btnSendRx219 = findViewById(R.id.btn_send_rx2_19);
        btnSendRx220 = findViewById(R.id.btn_send_rx2_20);
        btnSendRx221 = findViewById(R.id.btn_send_rx2_21);
        btnSendRx222 = findViewById(R.id.btn_send_rx2_22);

        btnSendRx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable
                        .create(new ObservableOnSubscribe<Integer>() {
                            @Override
                            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                                Log.e("TAG", "observable emit 1");
                                emitter.onNext(1);
                                Log.e("TAG", "observable emit 2");
                                emitter.onNext(2);
                                Log.e("TAG", "observable emit 3");
                                emitter.onNext(3);
                                emitter.onComplete();
                                Log.e("TAG", "observable emit 4");
                                emitter.onNext(4);
                            }
                        })
                        .subscribe(new Observer<Integer>() {

                            private Disposable disposable;

                            @Override
                            public void onSubscribe(Disposable d) {
                                Log.e("TAG", "onSubscribe isDisposed： " + d.isDisposed());
                                disposable = d;
                            }

                            @Override
                            public void onNext(Integer integer) {
                                Log.e("TAG", "onNet value: " + integer);
                                if (integer == 2) {
                                    disposable.dispose();
                                    Log.e("TAG", "onNext isDisposed： " + disposable.isDisposed());
                                }
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                            }

                            @Override
                            public void onComplete() {
                                Log.e("TAG", "onComplete");
                            }
                        });
            }
        });
        btnSendRx22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (disposable == null) {
                    Observable
                            .create(new ObservableOnSubscribe<Integer>() {
                                @Override
                                public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                                    emitter.onNext(1);
                                    Log.e("TAG", "发送数据");
                                    emitter.onNext(2);
                                    Log.e("TAG", "发送数据");
                                    emitter.onNext(3);
                                    Log.e("TAG", "发送数据");
                                    emitter.onNext(4);
                                    Log.e("TAG", "发送数据");
                                    emitter.onComplete();
                                }
                            })
                            .subscribeOn(Schedulers.computation())
                            //发送线程或阻塞等到处理线程结束之后再处理下一个数据
                            .map(new Function<Integer, String>() {
                                @Override
                                public String apply(Integer integer) throws Exception {
                                    Thread.sleep(4 * 1000);
                                    Log.e("TAG", "处理数据");
                                    return "This is result " + integer;
                                }
                            })
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<String>() {
                                @Override
                                public void onSubscribe(Disposable d) {
                                    Log.e("TAG", "onSubscribe");
                                    disposable = d;
                                }

                                @Override
                                public void onNext(String s) {
                                    Log.e("TAG", "next value:" + s);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                }

                                @Override
                                public void onComplete() {
                                    disposable = null;
                                }
                            });
                } else {
                    if (!disposable.isDisposed()) {
                        disposable.dispose();
                    }
                    disposable = null;
                }
            }
        });
        btnSendRx23.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Observable
                        .zip(getStringObservable(), getIntObservable(), new BiFunction<String, Integer, String>() {
                            @Override
                            public String apply(String o, Integer o2) throws Exception {
                                return o + o2;
                            }
                        })
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                Log.e("TAG", s);
                            }
                        }, new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                throwable.printStackTrace();
                            }
                        }, new Action() {
                            @Override
                            public void run() throws Exception {

                            }
                        });
            }
        });

        btnSendRx24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disposable disposable = Observable.concat(Observable.just(1, 2, 3), Observable.just(4, 5, 6))
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.e("TAG", "i = " + integer);
                            }
                        });
            }
        });

        btnSendRx25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disposable disposable = Observable.just(1, 2, 3)
                        .flatMap(new Function<Integer, ObservableSource<String>>() {
                            @Override
                            public ObservableSource<String> apply(Integer integer) throws Exception {
                                List<String> list = new ArrayList<>();
                                for (int i = 0; i < 3; i++) {
                                    list.add("I am value " + integer);
                                }
                                int delayTime = (int) (1 + Math.random() * 10);
                                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
                            }
                        })
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                Log.e("TAG", "s = " + s);
                            }
                        });
            }
        });

        btnSendRx26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Disposable disposable = Observable.just(1, 2, 3)
                        .concatMap(new Function<Integer, ObservableSource<String>>() {
                            @Override
                            public ObservableSource<String> apply(Integer integer) throws Exception {
                                List<String> list = new ArrayList<>();
                                for (int i = 0; i < 3; i++) {
                                    list.add("I am value " + integer);
                                }
                                int delayTime = (int) (1 + Math.random() * 10);
                                return Observable.fromIterable(list).delay(delayTime, TimeUnit.MILLISECONDS);
                            }
                        })
                        .subscribe(new Consumer<String>() {
                            @Override
                            public void accept(String s) throws Exception {
                                Log.e("TAG", "s = " + s);
                            }
                        });
            }
        });

        btnSendRx27.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Observable.just(1, 3, 2, 2, 3, 1, 1, 2, 3)
                        .distinct()
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.e("TAG", "i = " + integer);
                            }
                        });
            }
        });

        btnSendRx28.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Observable.just(1, 10, -12, 23, -333, 0)
                        .filter(new Predicate<Integer>() {
                            @Override
                            public boolean test(Integer integer) throws Exception {
                                return integer > 0;
                            }
                        })
                        .subscribe(new Consumer<Integer>() {
                            @Override
                            public void accept(Integer integer) throws Exception {
                                Log.e("TAG", "i = " + integer);
                            }
                        });
            }
        });

        btnSendRx29.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Observable.just(1, 2, 3, 4, 5)
                        .buffer(2, 1)//将原来的数，第一次从头开始读两个数(count),第二次从头开始，跳过1个数(skip)，读两个数，第三次从头开始，跳过2个数（skip*(size - 1))，读两个数...
                        .subscribe(new Consumer<List<Integer>>() {
                            @Override
                            public void accept(List<Integer> integer) throws Exception {
                                Log.e("TAG", "len = " + integer.size());
                                for (Integer i :
                                        integer) {
                                    Log.e("TAG", "i = " + i);
                                }
                            }
                        });
            }
        });

        btnSendRx210.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Log.e("TAG", "l = " + System.currentTimeMillis());
                Observable.timer(3, TimeUnit.SECONDS)
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(Long l) throws Exception {
                                Log.e("TAG", "l = " + l);
                                Log.e("TAG", "l = " + System.currentTimeMillis());
                            }
                        });
            }
        });

        btnSendRx211.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Log.e("TAG", "l = " + System.currentTimeMillis());
                if (disposable == null) {
                    disposable = Observable.interval(3, 3, TimeUnit.SECONDS)
                            .subscribe(new Consumer<Long>() {
                                @Override
                                public void accept(Long l) throws Exception {
                                    Log.e("TAG", "l = " + l);
                                }
                            });
                } else {
                    if (!disposable.isDisposed()) {
                        disposable.dispose();
                    }
                }
            }
        });

        btnSendRx212.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Log.e("TAG", "l = " + System.currentTimeMillis());
                if (disposable == null) {
                    disposable = Observable.just(1, 2, 3, 4, 5)
                            .doOnNext(new Consumer<Integer>() {
                                @Override
                                public void accept(Integer integer) throws Exception {
                                    Log.e("TAG", "doOnNext = " + integer);
                                }
                            })
                            .subscribe(new Consumer<Integer>() {
                                @Override
                                public void accept(Integer l) throws Exception {
                                    Log.e("TAG", "l = " + l);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    throwable.printStackTrace();
                                }
                            }, new Action() {
                                @Override
                                public void run() throws Exception {
                                    disposable = null;
                                }
                            });
                } else {
                    if (!disposable.isDisposed()) {
                        disposable.dispose();
                    }
                    disposable = null;
                }
            }
        });
        btnSendRx213.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Log.e("TAG", "l = " + System.currentTimeMillis());
                if (disposable == null) {
                    disposable = Observable.just(1, 2, 3, 4, 5)
                            .skip(2)
                            .subscribe(new Consumer<Integer>() {
                                @Override
                                public void accept(Integer l) throws Exception {
                                    Log.e("TAG", "l = " + l);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    throwable.printStackTrace();
                                }
                            }, new Action() {
                                @Override
                                public void run() throws Exception {
                                    disposable = null;
                                }
                            });
                } else {
                    if (!disposable.isDisposed()) {
                        disposable.dispose();
                    }
                    disposable = null;
                }
            }
        });
        btnSendRx214.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Log.e("TAG", "l = " + System.currentTimeMillis());
                if (disposable == null) {
                    disposable = Observable.just(1, 2, 3, 4, 5)
                            .take(2)
                            .subscribe(new Consumer<Integer>() {
                                @Override
                                public void accept(Integer l) throws Exception {
                                    Log.e("TAG", "l = " + l);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    throwable.printStackTrace();
                                }
                            }, new Action() {
                                @Override
                                public void run() throws Exception {
                                    disposable = null;
                                }
                            });
                } else {
                    if (!disposable.isDisposed()) {
                        disposable.dispose();
                    }
                    disposable = null;
                }
            }
        });
        btnSendRx215.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Log.e("TAG", "l = " + System.currentTimeMillis());
                if (disposable == null) {
                    Single.just(1)
                            .subscribe(new SingleObserver<Integer>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onSuccess(Integer integer) {
                                    Log.e("TAG", "onSuccess = " + integer);
                                    disposable = null;
                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                    disposable = null;
                                }
                            });
                } else {
                    if (!disposable.isDisposed()) {
                        disposable.dispose();
                    }
                    disposable = null;
                }
            }
        });

        btnSendRx216.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Log.e("TAG", "l = " + System.currentTimeMillis());
                if (disposable == null) {
                    Observable
                            .create(new ObservableOnSubscribe<Integer>() {
                                @Override
                                public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                                    emitter.onNext(22);
                                    SystemClock.sleep(600);
                                    emitter.onNext(33);
                                    SystemClock.sleep(410);
                                    emitter.onNext(44);
                                    SystemClock.sleep(400);
                                    emitter.onNext(55);
                                    SystemClock.sleep(600);
                                    emitter.onNext(66);
                                    SystemClock.sleep(600);
                                    emitter.onNext(77);
                                    SystemClock.sleep(600);
                                    emitter.onComplete();
                                }
                            })
                            .subscribeOn(Schedulers.newThread())
                            .debounce(500, TimeUnit.MILLISECONDS)
                            //这个东西的意义在于，去除发送过快的项，如果两个发送之间间隔太短，都会被去掉，只有当onNext调用后，下一个onNext调用大于
                            //timeout，上一个onNext才会被调用
                            .subscribe(new Consumer<Integer>() {
                                @Override
                                public void accept(Integer integer) throws Exception {
                                    Log.e("TAG", "integer = " + integer);
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    throwable.printStackTrace();
                                }
                            }, new Action() {
                                @Override
                                public void run() throws Exception {
                                    disposable = null;
                                }
                            });
                } else {
                    if (!disposable.isDisposed()) {
                        disposable.dispose();
                    }
                    disposable = null;
                }
            }
        });

        btnSendRx217.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Log.e("TAG", "l = " + System.currentTimeMillis());
                if (disposable == null) {
                    Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<? extends Integer>>() {
                        @Override
                        public ObservableSource<? extends Integer> call() throws Exception {
                            return Observable.just(1, 2, 3, 4, 5);//每次订阅都会新生成一个Observable，如果没有被订阅，就不会生成
                        }
                    });
                    observable.subscribe(new Observer<Integer>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Integer integer) {
                            Log.e("TAG", "integer = " + integer);
                        }

                        @Override
                        public void onError(Throwable e) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onComplete() {
                            disposable = null;
                        }
                    });
                } else {
                    if (!disposable.isDisposed()) {
                        disposable.dispose();
                    }
                    disposable = null;
                }
            }
        });

        btnSendRx218.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Log.e("TAG", "l = " + System.currentTimeMillis());
                if (disposable == null) {
                    disposable = Observable.just(1, 2, 3, 4, 5)
                            .last(4)
                            .subscribe(new Consumer<Integer>() {
                                @Override
                                public void accept(Integer integer) throws Exception {
                                    Log.e("TAG", "integer = " + integer);
                                    disposable = null;
                                }
                            }, new Consumer<Throwable>() {
                                @Override
                                public void accept(Throwable throwable) throws Exception {
                                    throwable.printStackTrace();
                                    disposable = null;
                                }
                            });
                } else {
                    if (!disposable.isDisposed()) {
                        disposable.dispose();
                    }
                    disposable = null;
                }
            }
        });

        btnSendRx219.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Log.e("TAG", "l = " + System.currentTimeMillis());
                if (disposable == null) {
                    Observable.merge(Observable.just(1, 2, 3, 4, 5), Observable.just(6, 7, 8, 9))
                            //相同类型的才能够合并
                            .subscribe(new Consumer<Integer>() {

                                @Override
                                public void accept(Integer s) throws Exception {
                                    Log.e("TAG", "integer = " + s);
                                    disposable = null;
                                }
                            });
                } else {
                    if (!disposable.isDisposed()) {
                        disposable.dispose();
                    }
                    disposable = null;
                }
            }
        });

        btnSendRx220.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Log.e("TAG", "l = " + System.currentTimeMillis());
                if (disposable == null) {
                    Observable.just(1, 2, 3, 4, 5)//合并所有数据
                            .reduce(new BiFunction<Integer, Integer, Integer>() {
                                @Override
                                public Integer apply(Integer integer, Integer integer2) throws Exception {
                                    return integer + integer2;
                                }
                            })
                            .subscribe(new Consumer<Integer>() {

                                @Override
                                public void accept(Integer s) throws Exception {
                                    Log.e("TAG", "integer = " + s);
                                    disposable = null;
                                }
                            });
                } else {
                    if (!disposable.isDisposed()) {
                        disposable.dispose();
                    }
                    disposable = null;
                }
            }
        });

        btnSendRx221.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Log.e("TAG", "l = " + System.currentTimeMillis());
                if (disposable == null) {
                    Observable.just(1, 2, 3, 4, 5)//合并所有数据
                            .scan(new BiFunction<Integer, Integer, Integer>() {
                                @Override
                                public Integer apply(Integer integer, Integer integer2) throws Exception {
                                    return integer + integer2;
                                }
                            })
                            .subscribe(new Consumer<Integer>() {

                                @Override
                                public void accept(Integer s) throws Exception {
                                    Log.e("TAG", "integer = " + s);
                                    disposable = null;
                                }
                            });
                } else {
                    if (!disposable.isDisposed()) {
                        disposable.dispose();
                    }
                    disposable = null;
                }
            }
        });

        btnSendRx222.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("CheckResult")
            @Override
            public void onClick(View v) {
                Log.e("TAG", "l = " + System.currentTimeMillis());
                if (disposable == null) {
                    Observable.interval(1, TimeUnit.SECONDS)//
                            .take(15)
                            .window(5, TimeUnit.SECONDS)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<Observable<Long>>() {
                                @Override
                                public void accept(Observable<Long> longObservable) throws Exception {
                                    Log.e("TAG", "accept");
                                    longObservable.subscribeOn(Schedulers.io())
                                            .subscribeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Consumer<Long>() {
                                                @Override
                                                public void accept(Long aLong) throws Exception {
                                                    Log.e("TAG", "aLong = " + aLong);
                                                    disposable = null;
                                                }
                                            });
                                }
                            });
                } else {
                    if (!disposable.isDisposed()) {
                        disposable.dispose();
                    }
                    disposable = null;
                }
            }
        });

    }

    private Disposable disposable;

    public Observable getStringObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("A");
                Log.e("TAG", "A");
                emitter.onNext("B");
                Log.e("TAG", "B");
                emitter.onNext("C");
                Log.e("TAG", "C");
                emitter.onNext("D");
                Log.e("TAG", "D");
                emitter.onComplete();
            }
        });
    }

    public Observable getIntObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                Log.e("TAG", "1");
                emitter.onNext(2);
                Log.e("TAG", "2");
                emitter.onNext(3);
                Log.e("TAG", "3");
                emitter.onComplete();
            }
        });
    }

}
