package com.spring.masinsa.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.masinsa.dto.ImageDTO;
import com.spring.masinsa.dto.MaskDTO;
import com.spring.masinsa.entity.Image;
import com.spring.masinsa.entity.Mask;
import com.spring.masinsa.entity.SoldoutStatus;
import com.spring.masinsa.mapper.MaskMapper;
import com.spring.masinsa.repository.MaskRepository;

@Service
public class MaskServiceImpl implements MaskService {
	
	@Autowired
	MaskRepository maskRepo;
	
	@Autowired
	MaskMapper maskMapper;
	
	@Override
	public MaskDTO getMask(Long maskId) {
		Mask mask = maskRepo.findMaskById(maskId);
		MaskDTO maskDTO = Mask.entityToDTO(mask);
		return maskDTO;
	}
	
	@Override
	public MaskDTO updateSoldout(Long maskId, String soldout) {
		Mask mask = maskRepo.findMaskById(maskId);
		if(mask != null) {
			if (soldout.equals("Y")) {
				mask.updateSoldout(SoldoutStatus.Y);
		} 
			else if (soldout.equals("N")) {
				mask.updateSoldout(SoldoutStatus.N);
		}
		maskRepo.save(mask);
		MaskDTO maskDTO = Mask.entityToDTO(mask);
		return maskDTO;
		}
		return null;
	}
	
	@Override
	public void updateClick(Long maskId) {
		Mask mask = maskRepo.findMaskById(maskId);
		mask.updateClick();
		maskRepo.save(mask);
	}
	
	@Override
	public List<ImageDTO> getAllImages(Long maskId) {
		List<Image> imageList = maskMapper.getAllImages(maskId);
		System.out.println(imageList);
		List<ImageDTO> imageDTOList = imageList.stream()
											.map(image -> Image.entityToDTO(image))
											.collect(Collectors.toList());
		return imageDTOList;
	}
	
	@Override
	public List<MaskDTO> getAllMask(String kf, String size, String shape) {
		List<Mask> maskList = maskMapper.getAllMask(kf, size, shape);
		List<MaskDTO> maskDTOList = maskList.stream()
											.map(mask -> Mask.entityToDTO(mask))
											.collect(Collectors.toList());
		return maskDTOList;
	}
	
	@Override
	public List<MaskDTO> getSortedMasks(String standard) {
		List<Mask> maskList = maskMapper.getSortedMasks(standard);
		List<MaskDTO> maskDTOList = maskList.stream()
											.map(mask -> Mask.entityToDTO(mask))
											.collect(Collectors.toList());
		return maskDTOList;
	}
	
}
