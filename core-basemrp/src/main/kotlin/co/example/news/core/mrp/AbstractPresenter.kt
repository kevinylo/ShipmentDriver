@file:Suppress("NULLABLE_TYPE_PARAMETER_AGAINST_NOT_NULL_TYPE_PARAMETER")

package co.example.news.core.mrp

import androidx.annotation.CallSuper
import com.uber.autodispose.ScopeProvider
import com.uber.autodispose.autoDispose
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber
import java.lang.IllegalArgumentException

/**
 * Abstract base class for all [Presenter]. For more details see:
 * https://docs.google.com/document/d/13x3eBzXVm8SzV2Z8W1GHOKDDVPhe9XWehR3fEwLazMM/edit#heading=h.nrnw03t7conb
 */
abstract class AbstractPresenter<R : Renderer<S>, S>(
  private val initial: S,
) : Presenter<R, S> {

  private val state: BehaviorSubject<S> = BehaviorSubject.createDefault(initial)
  protected var scope: ScopeProvider = ScopeProvider.UNBOUND
    private set
    get() {
      if (field == ScopeProvider.UNBOUND) {
        throw IllegalArgumentException("ScopeProvider not set. Call consume(Renderer) before using scope")
      }

      return field
    }

  @CallSuper
  override fun consume(renderer: R) {
    scope = renderer
    state
      .observeOn(AndroidSchedulers.mainThread())
      .autoDispose(scope)
      .subscribe({
        renderer.render(it)
      }, { e ->
        Timber.e(e, "render: $renderer, state: $state")
      })
  }

  final override fun emit(reducer: (S) -> S) {
    state.value?.let {
      state.onNext(reducer(it))
    }
  }

  final override fun emit(state: S) {
    this.state.onNext(state)
  }

  final override fun state(): S {
    return state.value ?: initial
  }

  final override fun states(): Observable<S> {
    return state.hide()
  }
}
