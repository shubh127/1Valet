1Valet Assignment


Language Used: Kotlin

Architectural Pattern: MVVM + Repository Pattern Feature Coverage:

Features Covered

	1	Device List
	
	2	Device Detail Page
	
	3	Favourite Device List
	
	4	Favourite / UnFavourite Device
	
	5	Search functionality on Device listing and Favourites
	

Package Structure:


After opening the application package (com.example.a1valet), we see the following structure:

• ‘data’ folder: Responsible for providing data to the UI Layer. Currently data is being fetched from mock JSON file and saved to DB which acts as single source of truth. It contains Repository, Database , DAO, DB Entities and Mock Network Layer. The data layer is reactive. It uses LiveData to hold its state, to which the UI layer listens via ViewModel and updates itself accordingly.

• ‘di’ folder: Contains dependency injection implementation using Hilt.

• ‘ui’ folder: The UI layer of the application. Contains Activity, Fragment, ViewModel, Adapters and ViewHolders etc. The ViewModel interacts with the repository implementation of data layer to fetch the data.

• ‘utils’ folder: Contains helper classes and constants. 

• ‘MyApplication’ file: Application file.


TechStack Used:


• Android Jetpack Navigation Component: To navigate between fragments.

• ViewModels: To fetch data from repository layer and provide it to ui layer.

• Coroutines: To handle threading.

• LiveData: To keep persistent and upto date data.

• Room DB: To implement offline cache of data.

• Glide: To load and cache images from URL.

• Hilt: To implement dependency injection.

• DataBinding: To bind data directly with xml views.

