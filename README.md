API Endpoints Documentation
Below is a list of the endpoints that i do for managing offers, properties, and contracts in the system. These endpoints allow for various actions such as submitting offers by investors, accepting offers by owners, searching for properties, adding properties by owners, and more. The system ensures proper handling of the relationships between owners, investors, properties, and contracts.
1: Allows an owner to add a new property.
2 :Allows an investor to submit an offer for a property.
3 :  Accepts an offer by the owner and creates a contract based on the offer details.
4 : Allows an investor to view only active properties.
5 : Allows an owner to search for properties by status (e.g., "active", "inactive")
6 : This endpoint allows an admin to view all properties in the system, including those that are pending activation or already active
7 : Allows an admin to activate a property and notify the owner.
8 : Allows an admin to reject a property if the owner fails to follow the instructions. The owner will receive an email notification regarding the rejection.
9 : This endpoint allows the owner to reject an offer if it does not meet their expectations.
