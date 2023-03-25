# scheduling driver for delivery given rules
## algorithm
- Generate suitability score on all the available deliveries for every driver
- Put the combined (driver, delivery, ss) into heap structure designed to sort based on descending suitability score with time complexity of O(n log n)
- Poll from the heap when there is still elements inside and the driver as well as the delivery was not obtained from the heap before (with the given assumption of each driver can only have 1 shipment and each shipment can only be offered to one driver)
- Produce a map from driver to assignment

## android architecture
Various modules
### app
- Thin layer of module primary for dagger injection as well as manifest in this case
- System wide initialization for libraries could also be in here
### mrp/mvi (core-basemrp)
- Model Renderer Presenter is a Model-View-Intent based paradigm focusing on states/intents and its unidirectional between presenter and the view
- Activity implements the renderer interface (for view initiated actions such as driver selected and pull down to refresh)
- Presenter emits states for activity to render on the ui level (states are just simple data classes with the same sealed interface)
- To assist with testing, code generation should be used to generate renderer test class which basically turned all the observable properties into behavior subjects (the code generation code is not included in this project)
### core-interface
- Contains interfaces for the manager, reposotory as well as a base class (handling scoping)
### repository
- If taking assignment instructions at face value there is really no need to have repository, so the assumptions here is the input json file is gonna be ingested down the road via the network.
- Input can be refreshed by user pulling down the drivers list
- There could also be some data persistence here as well depending on the need
### shipment manager
- Where most of the processing/logic takes place
- Depends on the repository and hides the repository related logic from the presenter
- Data processing from wire data types (dealing with vowels, suitability scores, etc)
- Also starts finding the optimal matching starting from initialization since input data has already been provided offline. 
- Can also optimize during runtime given user does pull to refresh
- Clear assignment data during refresh (using Optional concept to handle assigment call from the activity in the event when processing takes longer user will check back) 
- Goal is to provide presenter with data it needs (such as driver list and the assigned delivery)
- Manager is tested for its logic seperate from presenter testing (ShipmentManagerImplTest as an example)
### model
- Data classes (raw and internel)
- Could also seperate over the wire data class from internal data classes into seperate modules for cleaniness
### drivers
- Contains bits needed for this screen/screens (sub dagger component, states used by presenter, renderer interface, presenter, activity, etc)
- Each screen could be its own module or could also name the module more from a user/business perpective
- For example, this module could have one more screen/activity under a different package
### dagger
- App module provides general system resources to be injected
- Shipment module provides shipment manager, shipment repository, and provides input data (read from the packaged data.json file)
- Input file is packaged in the assets folder of this module for convenience.

## Assignment
- Everardo Welch "2431 Lindgren Corners" (ss: 11.25)
- Orval Mayert "79035 Shanna Light Apt. 322" (ss: 9.0)
- Howard Emmerich "1797 Adolf Island Apt. 744" (ss: 11.25)
- Izaiah Lowe "9856 Marvin Stravenue" (ss: 9.0)
- Monica Hermann "215 Osinski Manors" (ss: 11.25)
- Ellis Wisozk "75855 Dessie Lights" (ss: 7.0)
- Noemie Murphy "987 Champlin Lake" (ss: 10.5)
- Cleve Durgan "63187 Volkman Garden Suite 447" (ss: 9.0)
- Murphy Mosciski "8725 Aufderhar River Suite 859" (ss: 15.0)
- Kaiser Sose "7127 Kathlyn Ferry" (ss: 5.0)
