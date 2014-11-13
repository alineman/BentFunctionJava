findDouble = function(array){
	llong = length(array[,1])
	wwide = length(array[1,])
	equa = F
	for (i in 1:llong){
print(i)
		for(j in i:llong){
			ccount = 0
			for (k in 1:wwide){
				if (array[i,k] == array[j,k]){
					count = ccount +1
				}
			}
			if (ccount == 16) {
			return(T)
		}

	}

	}
	return(F)
}
saveImageToFile = function(matrix, filename){
	library(grid)
	matrixPic = as.matrix(matrix)
	width = length(matrixPic[1,])*4
	height = length(matrixPic[,1])*4
	png(filename = filename, width = width, height = height, pointsize=1)
	par(mar = rep(0, 4))
	grid.raster(matrixPic, interpolate=F)
	dev.off()

}