clear all
clc

data = importdata('hw2.data');
X = data(:,1:2);
y = data(:,3);
theta = zeros(size(X,2),1);

%Normal Equation
theta = pinv(X'*X)*X'*y;

%eigenvectors and eigenvalues
evec = eig(X'*X);
emax = max(evec);

%LMS algorithm
ntrial = 3; %number of different step length
steps = 300;
theta_lms_all = zeros(steps,ntrial*size(X,2));
sl = [2/emax;1/(2*emax);1/(8*emax)];
r = randi([1,size(X,1)],steps,1);
for j = 1 : ntrial
  theta_lms = zeros(size(X,2),1);
  theta_lms_path = zeros(steps,size(X,2));
  
  stepLength = sl(j);
  for i = 1 : steps
    theta_lms = theta_lms + stepLength*X(r(i),:)'*(y(r(i),:)-theta_lms'*X(r(i),:)');
    theta_lms_path(i,:) = theta_lms';
  end
  theta_lms_all(:,2*j-1:2*j) = theta_lms_path;
end

%Contour in parametre space
t1 = linspace(-0,1,100);
t2 = linspace(-0,0.6,100);
[theta1,theta2] = meshgrid(t1,t2);
J = zeros(size(theta1,1),size(theta1,2));

for i = 1 : size(theta1,1)
  for j = 1 : size(theta1,2) 
    J(i,j) = costFunc(X,y,[theta1(i,j);theta2(i,j)]);
  end
end

figure
contour(theta1,theta2,J,20);
xlabel("theta1")
ylabel("theta2")
title(['LMS Algorithm','(iteration = 300)'])
hold on 
a = plot(theta_lms_all(:,1),theta_lms_all(:,2),theta_lms_all(:,3),theta_lms_all(:,4),'r',...
         theta_lms_all(:,5),theta_lms_all(:,6),'k');
legend(a,"\\rho = 2/\\lambda_{max}","\\rho = 1/2\\lambda_{max}","\\rho = 1/8\\lambda_{max}")