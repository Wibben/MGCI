% SPH4U0
% Bing Li
% Mr. van Bemmel
% Assignment 0
% 4.3.2 - Algebra Engine, part iii) and iv)

% Data Points
x = [1.01, 2.2, 2.9, 4.03, 5.32, 6.22, 8.56, 9.09]; % 1x8 Matrix
y = [18.5; 76.2; 150.5; 365; 780; 1265; 3250; 7099]; % 8x1 Matrix
% Set up initial Vandermonde Matrix
V = ones(8,4);

% Fill matrix with correct elements
for i = 1:8
    for j = 1:4
        for f = j:3
            V(i,j) = V(i,j) * x(i);
        end
    end
end

% Calculations
Vt = V';
VtV = Vt*V;
iVtV = inv(VtV);
temp = iVtV * Vt;
c = temp * y;

% Display coefficients
c

% Creating functions to represent the line of best fit
fitx = linspace(1,10);
fity = c(1,1)*power(fitx,3) + c(2,1)*power(fitx,2) + c(3,1)*(fitx) + c(4,1);

% Plotting data
plot(x,y,'k.',fitx,fity,'k--'), % k is for black
axis([0,10,0,10000]), % Specifying range [x-min,x-max,y-min,y-max]
xlabel('x'), ylabel('y'), % Labelling the x and y axes
title('4.3.2 - x vs. y'), % Title of the graph
l = legend('Data','Fit','Location','northwest'); % Putting up a legend on the top left
title(l,'Legend') % Giving the legend a title
