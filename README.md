# GigHubGRepos

https://api.github.com 
GitHub API was used to get Google's public repos

https://api.github.com/users/google/repos?page=1&per_page=10
Application is build using MVVM architecture
First activity displays a list of google repos using RecyclerView + Paging library
Paging library helps to decrease consumption of internet traffic by loading fixed set of data
Initially 40 items are loaded, then 20 items.

https://api.github.com/repos/google/access-bridge-explorer/issues?page=1&per_page=10&state=open
By pressing on any item, second activity will be opened (list of issues) it has option to see ALL, OPEN and CLOSED issues
Activity also implemented RecyclerView + Pagind lib
