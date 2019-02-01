% SPH4U0
% Bing Li
% Mr. van Bemmel
% MPS14 - Calculus ABD 9e
% Page 891, 1-4

% Question 1
graph = figure(1); % Create a figure object, every graph gets its own window
t = linspace (0,2*pi);
x = 3*cos(t);
y = 3*sin(t);
v = pi/3; % Used to plot vectors

plot(x,y,'k'); hold on; % hold allows you to use multiple functions to plot on the same graph
plot(3*cos(v),3*sin(v),'.k','MarkerSize',15); % Marker for visibility
quiver(3*cos(v),3*sin(v),-3*sin(v),3*cos(v),'k'); % quiver(x(v),y(v),x'(v),y'(v)), this is velocity
quiver(3*cos(v),3*sin(v),-3*cos(v),-3*sin(v),'k'); % quiver(x(v),y(v),x''(v),y''(v)), this is acceleration
axis([-5,5,-5,5]); hold off % Always remember to turn off hold

% Position the axes at origin
ax = gca;
ax.XAxisLocation = 'origin';
ax.YAxisLocation = 'origin';
% Result: Circle

% Question 2
graph = figure(2); % Create a figure object, every graph gets its own window
t = linspace (-3,3);
x = t;
y = power(t,2);
v = 2; % Used to plot vectors
plot(x,y,'k'); hold on;
plot(v,v^2,'.k','MarkerSize',15); % Marker for visibility
quiver(v,v^2,1,2*v,'k'); % Velocity
quiver(v,v^2,0,2,'k'); % Acceleration
axis([-5,5,-1,9]); hold off

% Position the axes at origin
ax = gca;
ax.XAxisLocation = 'origin';
ax.YAxisLocation = 'origin';
% Result: Parabola

% Question 3
graph = figure(3); % Create a figure object, every graph gets its own window
t = linspace (-10,10);
x = exp(t);
y = exp(-t);
v = 0; % Used to plot vectors

% Position the axes at origin
plot(x,y,'k'); hold on;
plot(exp(v),exp(-v),'.k','MarkerSize',15); % Marker for visibility
quiver(exp(v),exp(-v),exp(v),-exp(-v),'k'); % Velocity
quiver(exp(v),exp(-v),exp(v),exp(-v),'k'); % Acceleration
axis([0,5,0,5]); hold off

ax = gca;
ax.XAxisLocation = 'origin';
ax.YAxisLocation = 'origin';
% Result: Something looking like 1/|x|

% Question 4
graph = figure(4); % Create a figure object, every graph gets its own window
t = linspace (-10,10);
x = 2+4*t;
y = 1-t;
v = 1; % Used to plot vectors

plot(x,y,'k'); hold on;
plot(2+4*v,1-v,'.k','MarkerSize',15); % Marker for visibility
quiver(2+4*v,1-v,4,-1,'k'); % Velocity
quiver(2+4*v,1-v,0,0,'k'); % Acceleration
axis([0,10,-5,5]); hold off

% Position the axes at origin
ax = gca;
ax.XAxisLocation = 'origin';
ax.YAxisLocation = 'origin';
% Result: Straight Line
