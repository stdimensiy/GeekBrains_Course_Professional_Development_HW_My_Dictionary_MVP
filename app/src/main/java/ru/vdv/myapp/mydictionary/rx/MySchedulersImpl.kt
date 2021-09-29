package ru.vdv.myapp.mydictionary.rx

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.vdv.myapp.mydictionary.rx.MySchedulers

class MySchedulersImpl : MySchedulers {

    override fun main(): Scheduler = AndroidSchedulers.mainThread()

    override fun io(): Scheduler = Schedulers.io()

    override fun computation(): Scheduler = Schedulers.computation()

    override fun newThread(): Scheduler = Schedulers.newThread()

    override fun single(): Scheduler = Schedulers.single()

    override fun trampoline(): Scheduler = Schedulers.trampoline()

    override fun start() = Schedulers.start()

    override fun shutdown() = Schedulers.shutdown()

}