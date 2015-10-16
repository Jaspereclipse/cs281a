function y_pred=pred_GLM(data,beta,nClass)

  X = data(:,1:3);
  y = data(:,4);
  
  pred = prob_GLM(X,beta,nClass);
  [~,y_pred] = max(pred,[],2);
  mis_rate = 1 - length(find(y==y_pred))/length(y);
  fprintf("The misclassification rate on is: %d%%\n",mis_rate*100);
  
end
