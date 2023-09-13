package com.snuquill.paperdx.article.infra;

import org.springframework.stereotype.Service;

import com.snuquill.paperdx.article.domain.Author;
import com.snuquill.paperdx.article.domain.AuthorService;
import com.snuquill.paperdx.member.domain.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

	private final MemberRepository memberRepository;
	private final AuthorConverter authorConverter;

	@Override
	public Author getAuthor(Long id) {
		return memberRepository.findById(id)
			.map(authorConverter::createAuthor)
			.orElseGet(Author::getAnonimousAuthor);
	}

}
