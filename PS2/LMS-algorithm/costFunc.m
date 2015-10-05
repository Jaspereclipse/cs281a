function J = costFunc(X,y,theta)

%Compute cost function
J = (y-X*theta)'*(y-X*theta);

end
