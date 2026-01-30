package com.mnn.realsr

class MnnSrNative {

    init {
        System.loadLibrary("MNN")
        System.loadLibrary("MNN_CL")
        System.loadLibrary("MNN_Vulkan")
        System.loadLibrary("mnnsr")
    }

    external fun run(
        inputPath: String,
        outputPath: String,
        scale: Int,
        tileSize: Int,
        modelPath: String,
        gpuIds: IntArray?,
        jobsLoad: Int,
        jobsSave: Int,
        verbose: Int,
        format: String?,
        backend: Int,
        colorType: Int,
        decensorMode: Int
    ): Int

    fun runDefault(
        inputPath: String,
        outputPath: String,
        modelPath: String,
        scale: Int = 4,
        tileSize: Int = 0,
        //gpu device to use (default=0) can be 0,1,2 for multi-gpu, -1 use cpu
        gpuIds: IntArray? = intArrayOf(-1),
        jobsSave: Int = 1,
        //verbose output
        verbose: Int = 0,
        format: String? = "png",
        //forward backend type(CPU=0,AUTO=4,CUDA=2,OPENCL=3,OPENGL=6,VULKAN=7,NN=5,USER_0=8,USER_1=9, default=3)
        backend: Int = 4,
        //model & output color space type (RGB=1, BGR=2, YCbCr=5, YUV=6, GRAY=10, GRAY model & YCbCr output=11, GRAY model & YUV output=12, default=1)
        colorType: Int = 1,
        //remove censor mode (Not=-1, Mosaic=0, default=-1)
        decensorMode: Int = -1
    ): Int {
        return run(
            inputPath = inputPath,
            outputPath = outputPath,
            scale = scale,
            tileSize = tileSize,
            modelPath = modelPath,
            gpuIds = gpuIds,
            jobsLoad = 1,
            jobsSave = jobsSave,
            verbose = verbose,
            format = format,
            backend = backend,
            colorType = colorType,
            decensorMode = decensorMode
        )
    }

    fun runDefaultJava(
        inputPath: String,
        outputPath: String,
        modelPath: String,
    ): Int {
        return runDefault(
            inputPath = inputPath,
            outputPath = outputPath,
            modelPath = modelPath,
        )
    }

}