package com.snuquill.paperdx.biz.article.infra;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.biz.article.domain.Author;
import com.snuquill.paperdx.biz.article.domain.AuthorRepository;
import com.snuquill.paperdx.biz.member.application.MemberDto;
import com.snuquill.paperdx.biz.member.application.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class DependenctyAuthorRepository implements AuthorRepository {

	private final MemberService memberService;
	private final AuthorConverter authorConverter;

	@Override
	public Author getAuthor(Long id) {
		MemberDto memberDto = memberService.getMemberDtoById(id);
		return authorConverter.createAuthor(memberDto);
	}

	@Override
	public List<Author> getAllAuthor(List<Long> idList) {
		return memberService.getAllMemberDtoByIdList(idList).stream().map(authorConverter::createAuthor).toList();
	}

}
