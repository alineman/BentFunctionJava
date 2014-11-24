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
	showImage(matrixPic)
	dev.off()

}

quickSaveImageToFile = function(dimensions, filename){
	library(grid)
	data = read.csv("data.csv", head=F)
	# mdata = t(as.matrix(data))
	fillingArray = rep(0,dimensions^2)
	for (i in data){
		fillingArray[i+1] = 1
	}
	dim(fillingArray) = c(dimensions, dimensions)
	matrixPic = as.matrix(fillingArray)

	width = dimensions*4
	height = dimensions*4
	png(filename = filename, width = width, height = height, pointsize=1)
	showImage(matrixPic)
	dev.off()

}

showImage = function(matrixes){
	library(grid)
	matrixPic = as.matrix(matrixes)
	par(mar = rep(0, 4))
	grid.raster(matrixes, interpolate=F)
}

calculateFast = function(dimensions){
	data = read.csv("data.csv", head=F)
	# mdata = t(as.matrix(data))
	fillingArray = rep(0,dimensions^2)
	for (i in data){
		fillingArray[i+1] = 1
	}
	dim(fillingArray) = c(dimensions, dimensions)
	showImage(t(fillingArray))
}

makeLines = function(matrixes){
	newMatrix = matrixes
	for (i in 1:256){
		for (j in 1:256){
			if (matrixes[i,j] > 1){
				# newMatrix[i,] = matrixes[i,]+0.5
				# newMatrix[,j] = matrixes[,j]+0.5
				newMatrix[i,j] = 1
			}
		}
	}
	return(newMatrix)
}