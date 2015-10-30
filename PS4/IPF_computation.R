#Initialization
init <- function(model=1){ # Graph models selection, default to be model 1
  nVal <- 4 # number of possible values in each clique potential
  phi <- c(1:nVal) # Initial assignment for clique(pair) potential
  if(model%%1==0) { # Check input argument
    if(model == 1){
      pairlist <- list(c(1,5),c(1,6),c(2,5),c(3,4),c(3,6),c(4,5),c(4,7),c(6,7)) # 8 pairs
      potential <- lapply(pairlist,list,phi)
    }
    else if(model==2){
      pairlist <- list(c(1,2),c(1,3),c(1,4),c(1,7),c(2,4),c(2,6),c(3,4),
                       c(3,5),c(3,7),c(4,6),c(5,7))  # 11 pairs
      potential <- lapply(pairlist,list,phi)
    }
    else if(model==3){
      pairlist <- list(c(1,2),c(1,3),c(1,4),c(1,5),c(1,6),c(1,7),c(2,3),
                       c(2,4),c(2,5),c(2,6),c(2,7),c(3,4),c(3,5),c(3,6),
                       c(3,7),c(4,5),c(4,6),c(4,7),c(5,6),c(5,7),c(6,7))  # 21 pairs
      potential <- lapply(pairlist,list,phi)
    }
    else{
      stop("Only 1,2 and 3 are accepted!")
    }
    return(potential)
  }
  else{
    stop("Invalid input!")
  }
}

#Computation of counts
pcount <- function(p){
  nObs <- dim(data)[1]
  count <- as.data.frame(table(data[p[[1]]]))[,3]/nObs
  return(count)
}


#Computation of marginal probability distribution
#mapping<- function(){
#  map <- matrix(0,ncol=n,nrow=dim(uni)[1])
#  for(i in 1 : n){
#   ind <- 2^uni[potential[[i]][[1]][1]] + 2^uni[potential[[i]][[1]][2]]
#   map[,i] = potential[[i]][[2]][ind]
#  }
#  phi_prod <- apply(map,1,prod)
#  return(phi_prod)
#}


#Computation of marginal probability distribution
mapping<- function(dat){
  map <- matrix(0,ncol=n,nrow=dim(dat)[1])
  for(i in 1 : n){
    ind <- as.matrix(dat[,potential[[i]][[1]][1]] + 2*dat[,potential[[i]][[1]][2]]+1)
    map[,i] = potential[[i]][[2]][ind]
  }
  phi_prod <- apply(map,1,prod)
  return(phi_prod)
}
