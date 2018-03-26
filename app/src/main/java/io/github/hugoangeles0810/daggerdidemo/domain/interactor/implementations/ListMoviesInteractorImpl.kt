package io.github.hugoangeles0810.daggerdidemo.domain.interactor.implementations

import io.github.hugoangeles0810.daggerdidemo.data.exceptions.NetworkException
import io.github.hugoangeles0810.daggerdidemo.data.exceptions.ServerException
import io.github.hugoangeles0810.daggerdidemo.data.repositories.MovieRepository
import io.github.hugoangeles0810.daggerdidemo.domain.executor.InteractorExecutor
import io.github.hugoangeles0810.daggerdidemo.domain.executor.MainThreadExecutor
import io.github.hugoangeles0810.daggerdidemo.domain.interactor.ListMoviesInteractor
import javax.inject.Inject

class ListMoviesInteractorImpl
    @Inject constructor(
        interactorExecutor: InteractorExecutor,
        mainThreadExecutor: MainThreadExecutor,
        private val moviesRepository: MovieRepository) : AbstractInteractor<ListMoviesInteractor.Callback, Any>(interactorExecutor, mainThreadExecutor),
        ListMoviesInteractor {

    override fun run() {
        try {
            val movies = moviesRepository.listMovies()
            mainThreadExecutor.execute(Runnable { mCallback.onSuccess(movies) })
        } catch (nex: NetworkException) {
            mainThreadExecutor.execute(Runnable { mCallback.onNetworkError() })
        } catch (srvx: ServerException) {
            mainThreadExecutor.execute(Runnable {
                mCallback.onError(srvx.message ?: "Error en el servidor :v")
            })
        }
    }
}