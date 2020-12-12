package org.apache.dubbo.common.compiler.support;

import org.junit.Test;

public class JavassistCompilerTest {

  @Test
  public void doCompile() throws Throwable {
    String name = "org.apache.dubbo.rpc.Protocol$Adaptive";
    String source = "package org.apache.dubbo.rpc;\n" +
        "import org.apache.dubbo.common.extension.ExtensionLoader;\n" +
        "public class Protocol$Adaptive implements org.apache.dubbo.rpc.Protocol {\n" +
        "public org.apache.dubbo.rpc.Exporter export(org.apache.dubbo.rpc.Invoker arg0) throws org.apache.dubbo.rpc.RpcException {\n" +
        "if (arg0 == null) throw new IllegalArgumentException(\"org.apache.dubbo.rpc.Invoker argument == null\");\n" +
        "if (arg0.getUrl() == null) throw new IllegalArgumentException(\"org.apache.dubbo.rpc.Invoker argument getUrl() == null\");\n" +
        "org.apache.dubbo.common.URL url = arg0.getUrl();\n" +
        "String extName = ( url.getProtocol() == null ? \"dubbo\" : url.getProtocol() );\n" +
        "if(extName == null) throw new IllegalStateException(\"Failed to get extension (org.apache.dubbo.rpc.Protocol) name from url (\" + url.toString() + \") use keys([protocol])\");\n" +
        "org.apache.dubbo.rpc.Protocol extension = (org.apache.dubbo.rpc.Protocol)ExtensionLoader.getExtensionLoader(org.apache.dubbo.rpc.Protocol.class).getExtension(extName);\n" +
        "return extension.export(arg0);\n" +
        "}\n" +
        "public org.apache.dubbo.rpc.Invoker refer(java.lang.Class arg0, org.apache.dubbo.common.URL arg1) throws org.apache.dubbo.rpc.RpcException {\n" +
        "if (arg1 == null) throw new IllegalArgumentException(\"url == null\");\n" +
        "org.apache.dubbo.common.URL url = arg1;\n" +
        "String extName = ( url.getProtocol() == null ? \"dubbo\" : url.getProtocol() );\n" +
        "if(extName == null) throw new IllegalStateException(\"Failed to get extension (org.apache.dubbo.rpc.Protocol) name from url (\" + url.toString() + \") use keys([protocol])\");\n" +
        "org.apache.dubbo.rpc.Protocol extension = (org.apache.dubbo.rpc.Protocol)ExtensionLoader.getExtensionLoader(org.apache.dubbo.rpc.Protocol.class).getExtension(extName);\n" +
        "return extension.refer(arg0, arg1);\n" +
        "}\n" +
        "public void destroy()  {\n" +
        "throw new UnsupportedOperationException(\"The method public abstract void org.apache.dubbo.rpc.Protocol.destroy() of interface org.apache.dubbo.rpc.Protocol is not adaptive method!\");\n" +
        "}\n" +
        "public int getDefaultPort()  {\n" +
        "throw new UnsupportedOperationException(\"The method public abstract int org.apache.dubbo.rpc.Protocol.getDefaultPort() of interface org.apache.dubbo.rpc.Protocol is not adaptive method!\");\n" +
        "}\n" +
        "}";
    JavassistCompiler javassistCompiler = new JavassistCompiler();
    Class<?> clazz = javassistCompiler.doCompile(name, source);

    System.out.println(clazz.getName());
  }

}
