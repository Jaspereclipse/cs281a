setwd("E:/Berkeley/CS281A/ps4")
source("IPF_computation.R")


##Read data as data.frame
data <- read.table("hw4data.data")
dim(data) # 500 * 7

for(s in 1:3){
	##Initialize
	potential <- init(s)
	nVal <- 4

	##Compute counts for each clique(empirical dist on one node)
	count <- lapply(potential,pcount)

	##IPF
	iter <- 40 # number of iterations
	#scene <- as.matrix(expand.grid(rep(list(c(1:nVal)),length(potential)-1)))
	#map <- matrix(0,ncol=dim(scene)[2],nrow=dim(scene)[1])
	n <- length(potential)
	uni <- unique(data)

	for(i in 1:iter){
	  #Get all the marginal probabilities
	  #Question: should we use the unique value to compute this?
	  f <- rep(0,4)
	  for(j in 1:n){
		phi_prod <- mapping(uni)
		clique <- potential[[j]][[1]] # current clique/pair index
		ind <- 1 + uni[,clique[1]] + 2*uni[,clique[2]]
		for(k in 1:nVal){
		  f[k] = sum(phi_prod[which(ind==k)])  
		}
		potential[[j]][[2]] = potential[[j]][[2]]*count[[j]]*(sum(f)/f)
	  }
	}

  #Print potential for clique {3,4}
  for (i in 1 : n){
    if(potential[[i]][[1]][1] == 3 & potential[[i]][[1]][2] == 4){
      print(potential[[i]][[2]])
    }
  }

	#Compute the likelihood
	prod <- mapping(data)
	pdist <- sum(log(prod/sum(prod)))

	print(pdist)

}














