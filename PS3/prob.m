function [mu,eta] = prob(X,beta,nClass)

  mu = zeros(size(X,1),nClass);
  numerator = exp(X*beta(:,1:(nClass-1)));
  denominator = 1 + sum(numerator,2);
  mu(:,1:(nClass-1)) = numerator./denominator;
  mu(:,nClass) = 1 - sum(mu,2);
  eta = log(mu) + log(denominator);

end