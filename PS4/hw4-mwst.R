setwd("E:/Berkeley/CS281A/ps4")
source("kruskal.R")

##Read data as data.frame
data <- read.table("hw4data.data")
dim(data) # 500 * 7
nObs <- dim(data)[1]
nNodes <- dim(data)[2] 
nPairs <- choose(nNodes,2)
pairlist <- list(c(1,2),c(1,3),c(1,4),c(1,5),c(1,6),c(1,7),c(2,3),
                 c(2,4),c(2,5),c(2,6),c(2,7),c(3,4),c(3,5),c(3,6),
                 c(3,7),c(4,5),c(4,6),c(4,7),c(5,6),c(5,7),c(6,7))  # 21 pairs

##Compute empirical marginals on nodes and pairs
pairCount <- lapply(pairlist,function(vec){
                                as.data.frame(table(data[vec]))[,3]/nObs
                             })
nodeCount <- lapply(c(1:nNodes),function(val){
                                as.data.frame(table(data[,val]))[,2]/nObs
                             })

##Compute the weight for each edge
weight <- rep(0,nPairs)
for(i in 1 : nPairs){
  first <- pairlist[[i]][1]
  second <- pairlist[[i]][2]
  denominator <- c(nodeCount[[first]][1]*nodeCount[[second]][1],
                   nodeCount[[first]][2]*nodeCount[[second]][1],
                   nodeCount[[first]][1]*nodeCount[[second]][2],
                   nodeCount[[first]][2]*nodeCount[[second]][2])
  weight[i] = sum(pairCount[[i]]*log(pairCount[[i]]/denominator))
}

df <- data.frame(cbind(t(matrix(unlist(pairlist),nrow=2)),weight))
order <- order(df$weight,decreasing=TRUE)

##Find the maximum spanning tree
s <- init(nNodes)
parent <- s[[1]]
rank <- s[[2]]

msTree <- list()

for (i in 1 : nPairs){
  u <- df[order[i],1]
  v <- df[order[i],2]
  if(find(u) != find(v)){
    msTree[[i]] <- c(u,v) 
    union(u,v)
  }
}

msTree <- t(matrix(unlist(msTree),nrow=2))



