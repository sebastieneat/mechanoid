/*
 * generated by Xtext
 */
package com.robotoworks.mechanoid.ops.generator

import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.generator.IGenerator
import org.eclipse.xtext.generator.IFileSystemAccess
import com.robotoworks.mechanoid.ops.opServiceModel.Model
import com.google.inject.Inject
import static extension com.robotoworks.mechanoid.text.Strings.*
import static extension com.robotoworks.mechanoid.ops.generator.Extensions.*
import com.robotoworks.mechanoid.generator.MechanoidOutputConfigurationProvider
import com.robotoworks.mechanoid.ops.opServiceModel.Operation

class OpServiceModelGenerator implements IGenerator {
	
	@Inject OperationGenerator mOperationGenerator
	@Inject ServiceConfigurationGenerator mServiceConfigGenerator
	@Inject ServiceGenerator mServiceGenerator
	@Inject OperationProcessorGenerator mOperationProcessorGenerator
	@Inject OperationRegistryGenerator mOperationRegistryGenerator
	
	override void doGenerate(Resource resource, IFileSystemAccess fsa) {
		var model = resource.contents.head as Model;
			
		generateOperationProcessor(resource, fsa)
		generateService(resource, fsa)
		generateServiceConfiguration(resource, fsa)
		generateOperationRegistry(resource, fsa)
			
		model.service.ops.forEach[
			item|generateOps(resource, fsa, item)
		];	
	}
	
	def generateOps(Resource resource, IFileSystemAccess fsa, Operation op) {
		
		var model = resource.contents.head as Model;
		
		fsa.generateFile(
			model.packageName.resolveFileName("Abstract".concat(op.name.pascalize).concat("Operation")), 
			mOperationGenerator.generate(model, op)
		);
		
		fsa.generateFile(
			model.packageName.resolveFileName(op.name.pascalize.concat("Operation")), 
			MechanoidOutputConfigurationProvider::DEFAULT_STUB_OUTPUT,
			mOperationGenerator.generateStub(model, op)
		);
	}
	
	def generateOperationRegistry(Resource resource, IFileSystemAccess fsa) {
		
		var model = resource.contents.head as Model;
		
		fsa.generateFile(
			model.packageName.resolveFileName("Abstract".concat(model.service.name.formatServiceName).concat("OperationConfigurationRegistry")), 
			mOperationRegistryGenerator.generate(model)
		);
		
		fsa.generateFile(
			model.packageName.resolveFileName(model.service.name.formatServiceName.concat("OperationConfigurationRegistry")), 
			MechanoidOutputConfigurationProvider::DEFAULT_STUB_OUTPUT,
			mOperationRegistryGenerator.generateStub(model)
		);
	}
	
	def generateServiceConfiguration(Resource resource, IFileSystemAccess fsa) {
		
		var model = resource.contents.head as Model;
		
		fsa.generateFile(
			model.packageName.resolveFileName("Abstract".concat(model.service.name.pascalize).concatOnce("Service").concat("Configuration")), 
			mServiceConfigGenerator.generate(model)
		);
		
		fsa.generateFile(
			model.packageName.resolveFileName(model.service.name.pascalize.concatOnce("Service").concat("Configuration")), 
			MechanoidOutputConfigurationProvider::DEFAULT_STUB_OUTPUT,
			mServiceConfigGenerator.generateStub(model)
		);
	}

	def generateService(Resource resource, IFileSystemAccess fsa) {
		
		var model = resource.contents.head as Model;
		
		fsa.generateFile(
			model.packageName.resolveFileName("Abstract".concat(model.service.name.pascalize).concatOnce("Service")), 
			mServiceGenerator.generate(model)
		);
		
		fsa.generateFile(
			model.packageName.resolveFileName(model.service.name.pascalize.concatOnce("Service")), 
			MechanoidOutputConfigurationProvider::DEFAULT_STUB_OUTPUT,
			mServiceGenerator.generateStub(model)
		);
	}
	
	def generateOperationProcessor(Resource resource, IFileSystemAccess fsa) {
		
		var model = resource.contents.head as Model;
		
		fsa.generateFile(
			model.packageName.resolveFileName("Abstract".concat(model.service.name.pascalize).concat("Processor")), 
			mOperationProcessorGenerator.generate(model)
		);
		
		fsa.generateFile(
			model.packageName.resolveFileName(model.service.name.pascalize.concat("Processor")), 
			MechanoidOutputConfigurationProvider::DEFAULT_STUB_OUTPUT,
			mOperationProcessorGenerator.generateStub(model)
		);
	}
}
