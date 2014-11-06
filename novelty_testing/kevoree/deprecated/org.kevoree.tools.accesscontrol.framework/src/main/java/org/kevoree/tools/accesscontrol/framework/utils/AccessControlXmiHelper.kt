package org.kevoree.tools.accesscontrol.framework.utils


import java.util.zip.Deflater
import java.util.zip.Inflater
import java.io.*
import org.kevoree.accesscontrol.AccessControlPolicy;
import org.kevoree.accesscontrol.serializer.ModelSerializer
import org.kevoree.accesscontrol.serializer.XMIModelSerializer
import org.kevoree.framework.ZipUtil
import org.kevoree.accesscontrol.loader.ModelLoader
import org.kevoree.accesscontrol.loader.XMIModelLoader

object AccessControlXmiHelper {


    fun save(uri: String, root: AccessControlPolicy) {
        //CHECK DIRECTORY CREATION
        val folderUri = if(uri.contains(File.separator)){
            uri.substring(0, uri.lastIndexOf(File.separator))
        } else {
            uri
        }
        //  val folderUri = uri.substring(0,uri.lastIndexOf(File.separator))
        val folder = File(folderUri)
        if (!folder.exists()){
            folder.mkdirs()
        }
        val serializer = XMIModelSerializer()
        val outputFile = File(uri);
        if(!outputFile.exists()) {
            outputFile.createNewFile()
        }
        val fop = FileOutputStream(outputFile)
        serializer.serialize(root, fop)
        fop.flush()
        fop.close()
    }

    fun saveToString(root: AccessControlPolicy, prettyPrint: Boolean): String {
        val serializer = XMIModelSerializer()
        val ba = ByteArrayOutputStream()
        val res = serializer.serialize(root, ba)
        ba.flush()
        val result = String(ba.toByteArray())
        ba.close()
        return result
    }

    fun loadString(model: String): AccessControlPolicy? {
        val loader = XMIModelLoader()
        val loadedElements = loader.loadModelFromString(model)
        if(loadedElements != null && loadedElements.size() > 0) {
            return loadedElements.get(0) as AccessControlPolicy;
        } else {
            return null;
        }
    }

    fun load(uri: String): AccessControlPolicy? {
        val loader = XMIModelLoader()
        val loadedElements = loader.loadModelFromPath(File(uri))
        if(loadedElements != null && loadedElements.size() > 0) {
            return loadedElements.get(0) as AccessControlPolicy;
        } else {
            return null;
        }
    }

    fun loadStream(input: InputStream): AccessControlPolicy? {
        val loader = XMIModelLoader()
        val loadedElements = loader.loadModelFromStream(input)
        if(loadedElements != null && loadedElements.size() > 0) {
            return loadedElements.get(0) as AccessControlPolicy;
        } else {
            return null;
        }

    }

    fun saveStream(output: OutputStream, root: AccessControlPolicy): Unit {
        val serializer = XMIModelSerializer()
        val result = serializer.serialize(root, output)
    }

    fun saveCompressedStream(output: OutputStream, root: AccessControlPolicy): Unit {
        val modelStream = ByteArrayOutputStream()
        saveStream(modelStream, root)
        output.write(ZipUtil.compressByteArray(modelStream.toByteArray()))
        output.flush()

    }

    fun loadCompressedStream(input: InputStream): AccessControlPolicy? {
        val inputS = ByteArrayInputStream(ZipUtil.uncompressByteArray(input.readBytes(input.available())))
        return loadStream(inputS)
    }



}