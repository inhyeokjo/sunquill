package com.snuquill.paperdx.biz.article.infra;

import java.util.List;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.biz.article.domain.Author;
import com.snuquill.paperdx.biz.article.domain.AuthorRepository;
import com.snuquill.paperdx.biz.member.domain.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthorRepositoryImpl implements AuthorRepository {

	private final MemberRepository memberRepository;
	private final AuthorConverter authorConverter;

	@Override
	public Author getAuthor(Long id) {
		return memberRepository.findById(id)
			.map(authorConverter::createAuthor)
			.orElseGet(Author::getAnonimousAuthor);
	}

	@Override
	public List<Author> getAllAuthor(List<Long> idList) {
		return memberRepository.findAllById(idList)
			.stream()
			.map(authorConverter::createAuthor)
			.toList();
	}

}
