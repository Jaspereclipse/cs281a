function mu = prob_GLM(X,beta,nClass)
  mu = (2*pi)^(-nClass/2)*exp(-1/2*(1-X*beta).*(1-X*beta));
  normalization = sum(mu,2);
  mu = mu./normalization;
end