package com.github.anastasiazhukova.lib.threading;

import com.github.anastasiazhukova.lib.threading.executors.IExecutor;

public interface IThreadingManager {

    IExecutor getExecutor(ExecutorType pType);

    final class Imlp {

        private static ThreadingManager.Config mConfig;

        public static void setConfig(final ThreadingManager.Config pConfig) {
            mConfig = pConfig;
        }

        public static IThreadingManager getThreadingManager() {
            if (mConfig == null) {
                return new ThreadingManager(ThreadingManager.Config.getDefaultConfig());
            } else {
                return new ThreadingManager(mConfig);
            }
        }

        public static IThreadingManager getThreadingManager(ThreadingManager.Config pConfig) {
            return new ThreadingManager(mConfig);
        }

    }

}
