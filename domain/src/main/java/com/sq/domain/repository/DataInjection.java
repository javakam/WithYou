package com.sq.domain.repository;

import android.content.Context;

import com.sq.data.wandroid.repository.ILocalRepository;
import com.sq.data.wandroid.repository.INetRepository;
import com.sq.domain.net.api.IWanApi;
import com.sq.domain.net.http.RetrofitModule;
import com.sq.domain.repository.local.LocalRepositoryImpl;
import com.sq.domain.repository.server.NetRepositoryImpl;

/**
 * Created by javakam on 2018/5/23.
 */
class DataInjection {
    private static Repository sInstance;

    public static Repository getRepository(Context context, String baseUrl) {
        if (sInstance == null) {
            synchronized (Repository.class) {
                if (sInstance == null) {
                    sInstance = createRepository(context, baseUrl);
                }
            }
        }
        return sInstance;
    }
    public static void setRepository(Repository repository) {
        sInstance = repository;
    }

    private static INetRepository getServerRepository(String baseUrl) {
        IWanApi requestApi = RetrofitModule.getRequestApi(baseUrl);
        INetRepository serverRepository = new NetRepositoryImpl(requestApi);
        return serverRepository;
    }
//    private static ILocalRepository getLocalRepository(IBasicServiceDao basicServiceDao,
//                                                       IInspectionServiceDao inspectionServiceDao,
//                                                       IBusinessService businessServiceDao,
//                                                       IReferenceServiceDao referenceServiceDao,
//                                                       ITransferServiceDao transferServiceDao,
//                                                       ICheckServiceDao checkServiceDao) {
//        ILocalRepository localRepository = new LocalRepositoryImpl(basicServiceDao, inspectionServiceDao, businessServiceDao,
//                referenceServiceDao, transferServiceDao, checkServiceDao);
//        return localRepository;
//    }

    private static Repository createRepository(Context context, String baseUrl) {
//        ILocalRepository localRepository = getLocalRepository(basicServiceDao, inspectionServiceDao,
//                businessService, referenceServiceDao, transferServiceDao, checkServiceDao);
        INetRepository serverRepository = getServerRepository(baseUrl);
//        return new Repository(serverRepository, localRepository);
        return new Repository(serverRepository);
    }
}
