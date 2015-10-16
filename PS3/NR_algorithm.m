function beta_new = NR_algorithm(X,y,beta)
  
  nClass = size(y,2);
  
  %Calculate probabilty mu and eta for each class
  [mu,eta] = prob(X,beta,nClass);
  i=1;
  for i = 1 : nClass
    %Calculate weighted matrix W
    W = diag(mu(:,i).*(1-mu(:,i)));
    %Calculate surrogate z
    z = eta(:,i) + pinv(W)*(y(:,i)-mu(:,i));
    %update beta
    beta_new(:,i) = pinv(X'*W*X)*X'*W*z;
  end
end
