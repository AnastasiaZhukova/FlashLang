package com.github.anastasiazhukova.lib.threading;

import com.github.anastasiazhukova.lib.threading.executors.IExecutor;

public interface IThreadingManager {

    IExecutor getExecutor(ExecutorType pType);

    final class Imlp {

        public static IThreadingManager getThreadingManager(){
            return new ThreadingManager(ThreadingManager.Config.getDefaultConfig());
        }

        public static IThreadingManager getThreadingManager(final ThreadingManager.Config pConfig) {
            if (pConfig != null) {
                return new ThreadingManager(pConfig);
            } else {
                throw new IllegalStateException("Wrong config");
            }
        }
    }

}
