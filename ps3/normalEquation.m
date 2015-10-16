function beta_new = normalEquation(X,y)
  beta_new = pinv(X'*X)*X'*y;
end