package co.example.news.core.mrp

import com.uber.autodispose.ScopeProvider

interface Renderer<in S> : ScopeProvider {
  /**
   * Render screen from a given state
   *
   * @param state The state to render
   */
  fun render(state: S)
}
