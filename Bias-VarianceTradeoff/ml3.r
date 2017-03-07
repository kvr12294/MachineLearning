x <- sort(runif(20, 0 , 5))


y <- (2 * cos(2.8 * x) + 7.5) 
plot(x, y, main="(a) cos function", type = 'l', ylab = "f(x)", xlab = "x", col = "black", pch = 3)

noise <- rnorm(length(x), mean = 0, sd = 1)

fun <- y + noise 
points(x, fun)
#plot(x, fun, main="(b) cos function with added noise", type = 'l', ylab = "f(x)", xlab = "x", col = "black", pch = 15)
lines(x, y, col = 'red', lwd = 3)

set_instance <- vector("list", 100)

for(i in 1:100)
	set_instance[[i]] <- c(sort(runif(20, 0 , 5)))

for(i in 1:5)
{
	#set1 <- sort(runif(20, 0 , 5))
	y <- (2 * cos(2.8 * set1) + 7.5) 
	noise <- rnorm(length(x), mean = 0, sd = 1)
	m1 <- noise + y
	model1 <- lm(m1 ~ set1 + I(set1^2) + I(set1^3))
	plot(set1, model1, main="order 3", type = 'l', ylab = "f(x)", xlab = "x", col = "blue")	
}






model1 <- lm(fun ~ x + I(X^2) + I(X^3))


o1 <- lm(fun ~ x + I(x) + I(x))
o2 <- lm(y ~ x + I(x) + I(x^2))
o3 <- lm(fun ~ x + I(x^2) + I(x^3))