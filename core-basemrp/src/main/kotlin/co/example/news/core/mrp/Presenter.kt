package co.example.news.core.mrp

import io.reactivex.Observable

interface Presenter<R : Renderer<S>, S> {

  /**
   * Consume [renderer]'s input streams. This is where we configure a
   * all core logic of presenter
   *
   * @param renderer A renderer which provides user input events
   */
  fun consume(renderer: R)

  /**
   * Emit state by applying [reducer] function on the latest state
   *
   * @param reducer The reducer functor to transform state
   */
  fun emit(reducer: (S) -> S)

  /**
   * Emit a single state
   *
   * @param state A state to be emitted
   */
  fun emit(state: S)

  /**
   * Return the latest state
   */
  fun state(): S

  /**
   * Return state stream as [Observable]
   */
  fun states(): Observable<S>
}
