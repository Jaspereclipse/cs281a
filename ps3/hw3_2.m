%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%%Statistical Learning Theory
%%Problem set #3
%%Juanyan Li Oct 2015
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
clear all
clc

train_data = load('hw3-2-train.data');
test_data = load("hw3-2-test.data");

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%2(a) Newton-Raphson Algorithm
%  maximizing log likelihood of logistic regression
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
X = train_data(:,1:3);
nObs = size(X,1);
nClass = length(unique(train_data(:,4)));
y = zeros(nObs,nClass);
y = [train_data(:,4)==1,train_data(:,4)==2,train_data(:,4)==3,train_data(:,4)==4];
beta = zeros(size(X,2),nClass);
steps = 100;

for j = 1 : steps
    beta = NR_algorithm(X,y,beta);
end

%%Apply logistic regression model on training data
pred_train_LoR = pred(train_data,beta,nClass,1);

%%Apply logistic regression model on testing data
pred_test_LoR = pred(test_data,beta,nClass,1);

%%Plotting
%%neglect the outliers
plotData(train_data,beta,nClass); 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%2(b) General Linear Model
%  maximum likelihood estimates
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
beta_neq = normalEquation(X,y);

%%Apply model on training data
pred_train_LiR = pred_GLM(train_data,beta_neq,nClass);

%%Apply model on testing data
pred_test_LiR = pred_GLM(test_data,beta_neq,nClass);

%%Plotting
%%neglect the outliers
plotData(train_data,beta_neq,nClass); 
