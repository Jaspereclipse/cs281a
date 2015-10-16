function plotData(data,beta,nClass)

  figure
  %assume four classes
  marker = ["r+";"go";"b*";"cx"];
  X = data(:,1:size(data,2)-2);
  %Data points
  for i = 1 : nClass
    classInd = find(data(:,4)==i); 
    plot(X(classInd,1),X(classInd,2),marker(i,:),'MarkerSize',7)
    hold on
  end
  %axis configuration
  axis([-2 2 -2 2])
  %label, title
  title("Multiclass Classification")
  xlabel("x1")
  ylabel("x2")
  
  %boundries
  x1 = linspace(-2,2);
  x2 = linspace(-2,2);
  [X1, X2] = meshgrid(x1,x2);
  label = zeros(size(X1));
  one = ones(size(X1,1));
  for i = 1 : size(X1,2)
    meshdata = [X1(:,i) X2(:,i) one];
    label(:,i) = pred(meshdata,beta,nClass,0);
  end
  contour(X1,X2,label)

end