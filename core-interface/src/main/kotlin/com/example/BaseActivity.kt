package com.example

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uber.autodispose.lifecycle.CorrespondingEventsFunction
import com.uber.autodispose.lifecycle.LifecycleScopeProvider
import com.uber.autodispose.lifecycle.LifecycleScopes
import io.reactivex.CompletableSource
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

open class BaseActivity : AppCompatActivity(), LifecycleScopeProvider<BasicScopeEvent> {

  val scopeProvider: LifecycleScopeProvider<BasicScopeEvent> by lazy { this }
  private val lifecycleEvents = BehaviorSubject.create<BasicScopeEvent>()

  override fun onCreate(savedInstanceState: Bundle?) {
    lifecycleEvents.onNext(BasicScopeEvent.ATTACH)
    super.onCreate(savedInstanceState)
  }

  override fun onDestroy() {
    lifecycleEvents.onNext(BasicScopeEvent.DETACH)
    super.onDestroy()
  }

  override fun lifecycle(): Observable<BasicScopeEvent> {
    return lifecycleEvents.hide()
  }

  override fun correspondingEvents(): CorrespondingEventsFunction<BasicScopeEvent> {
    return BasicScopeEvent.CORRESPONDING_EVENTS
  }

  override fun peekLifecycle(): BasicScopeEvent? {
    return lifecycleEvents.value
  }

  override fun requestScope(): CompletableSource {
    return LifecycleScopes.resolveScopeFromLifecycle(this)
  }
}

enum class BasicScopeEvent {
  ATTACH, DETACH;

  companion object {
    val CORRESPONDING_EVENTS = CorrespondingEventsFunction<BasicScopeEvent> { event ->
      when (event) {
        ATTACH -> DETACH
        else -> {
          throw RuntimeException("Cannot bind to Activity lifecycle after destroy.")
        }
      }
    }
  }
}