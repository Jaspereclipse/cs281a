#Kruskal's algorithm


init <- function(n){
  return(list(c(1:n),rep(0,n)))
}

find <- function(u){
  while(.GlobalEnv$parent[u] != u)
    u = .GlobalEnv$parent[u]
  return(u)
}

union <- function(u,v){
  ru <- find(u)
  rv <- find(v)
  if(ru == rv) {
    return
  }
  if(.GlobalEnv$rank[ru] > .GlobalEnv$rank[rv]){
    .GlobalEnv$parent[rv] = ru
  }
  else{
    .GlobalEnv$parent[ru] = rv
    if(.GlobalEnv$rank[ru] == .GlobalEnv$rank[rv]){
    }
  }
}