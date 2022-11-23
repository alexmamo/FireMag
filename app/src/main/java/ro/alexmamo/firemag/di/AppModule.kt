package ro.alexmamo.firemag.di

import android.app.Application
import android.content.Context
import androidx.paging.PagingConfig
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ro.alexmamo.firemag.R
import ro.alexmamo.firemag.core.AppConstants.SIGN_IN_REQUEST
import ro.alexmamo.firemag.core.AppConstants.SIGN_UP_REQUEST
import ro.alexmamo.firemag.core.FirebaseConstants.PAGE_SIZE
import ro.alexmamo.firemag.data.repository.*
import ro.alexmamo.firemag.domain.repository.*
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    fun provideFirebaseAuth() = Firebase.auth

    @Provides
    fun provideFirebaseDatabase() = Firebase.database


    @Provides
    fun provideFirebaseFirestore() = Firebase.firestore

    @Provides
    fun provideOneTapClient(
        @ApplicationContext
        context: Context
    ) = Identity.getSignInClient(context)

    @Provides
    @Named(SIGN_IN_REQUEST)
    fun provideSignInRequest(
        app: Application
    ) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(app.getString(R.string.web_client_id))
                .setFilterByAuthorizedAccounts(true)
                .build())
        .setAutoSelectEnabled(true)
        .build()

    @Provides
    @Named(SIGN_UP_REQUEST)
    fun provideSignUpRequest(
        app: Application
    ) = BeginSignInRequest.builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setServerClientId(app.getString(R.string.web_client_id))
                .setFilterByAuthorizedAccounts(false)
                .build())
        .build()

    @Provides
    fun provideGoogleSignInOptions(
        app: Application
    ) = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestIdToken(app.getString(R.string.web_client_id))
        .requestEmail()
        .build()

    @Provides
    fun provideAuthRepository(
        auth: FirebaseAuth,
        oneTapClient: SignInClient,
        @Named(SIGN_IN_REQUEST)
        signInRequest: BeginSignInRequest,
        @Named(SIGN_UP_REQUEST)
        signUpRequest: BeginSignInRequest,
        db: FirebaseFirestore
    ): AuthRepository = AuthRepositoryImpl(
        auth = auth,
        oneTapClient = oneTapClient,
        signInRequest = signInRequest,
        signUpRequest = signUpRequest,
        db = db
    )

    @Provides
    fun provideMainRepository(
        auth: FirebaseAuth,
        oneTapClient: SignInClient,
        firebaseDatabase: FirebaseDatabase,
        firebaseFirestore: FirebaseFirestore,
        config: PagingConfig
    ): MainRepository = MainRepositoryImpl(
        auth = auth,
        oneTapClient = oneTapClient,
        firebaseDatabase = firebaseDatabase,
        firebaseFirestore = firebaseFirestore,
        config = config
    )

    @Provides
    fun provideBrandProductsRepository(
        firebaseFirestore: FirebaseFirestore,
        config: PagingConfig
    ): BrandProductsRepository = BrandProductsRepositoryImpl(
        db = firebaseFirestore,
        config = config
    )

    @Provides
    fun provideProductDetailsRepository(
        firebaseDatabase: FirebaseDatabase,
        firebaseFirestore: FirebaseFirestore,
        auth: FirebaseAuth
    ): ProductDetailsRepository = ProductDetailsRepositoryImpl(
        firebaseDatabase = firebaseDatabase,
        firebaseFirestore = firebaseFirestore,
        auth = auth
    )

    @Provides
    fun provideProductSearchRepository(
        firebaseFirestore: FirebaseFirestore,
        config: PagingConfig
    ): ProductSearchRepository = ProductSearchRepositoryImpl(
        db = firebaseFirestore,
        config = config
    )

    @Provides
    fun provideShoppingCartRepository(
        firebaseDatabase: FirebaseDatabase,
        firebaseFirestore: FirebaseFirestore,
        auth: FirebaseAuth
    ): ShoppingCartRepository = ShoppingCartRepositoryImpl(
        firebaseDatabase = firebaseDatabase,
        firebaseFirestore = firebaseFirestore,
        auth = auth
    )
    @Provides
    fun provideOrderDetailsRepository(
        firebaseFirestore: FirebaseFirestore,
        auth: FirebaseAuth
    ): ProductsOrderRepository = ProductsOrderRepositoryImpl(
        db = firebaseFirestore,
        auth = auth
    )

    @Provides
    fun providePagingConfig() = PagingConfig(
        pageSize = PAGE_SIZE.toInt()
    )
}