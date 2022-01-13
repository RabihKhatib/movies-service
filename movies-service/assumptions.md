OMDB Api Key is assumed to be lifetime (stored inside code) if not it can be stored in a property file
No user interface is needed
Used token is "Bearer Token" and a service to /register user is created to generate /token by user
CSV data file shouldn't be loaded into DB (memory or file)
For rating a user (person) table is created
Rating is stored by user
Rating is between 1 and 10
Top 10 rated movies are read from local data (average rating of all users by movie is calculated)
Top 10 movies are sorted according to the OMDB BoxOffice data
The API considers type as movies only, from OMDB
