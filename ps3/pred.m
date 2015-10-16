function y_pred = pred(data,beta,nClass,display)
  
  X = data(:,1:3);
  y = data(:,4);
  
  pred = prob(X,beta,nClass);
  [~,y_pred] = max(pred,[],2);
  mis_rate = 1 - length(find(y==y_pred))/length(y);
  if display == 1
    fprintf("The misclassification rate on training set is: %d%%\n",mis_rate*100);
end